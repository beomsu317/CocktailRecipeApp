package com.beomsu317.cocktailrecipeapp.presentation.category.category_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases
) : ViewModel() {

    var state by mutableStateOf(CategoryListState())
        private set

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    init {
        getCategories()
    }

    fun getCategories() {
        cocktailUseCases.getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        categories = result?.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(
                        OneTimeEvent.ShowSnackbar(
                            result?.message ?: "An unexpected error occured"
                        )
                    )
                    state = state.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}