package com.beomsu317.cocktailrecipeapp.presentation.category_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CategoryListState())
    val state: State<CategoryListState> = _state

    private val _oneTimeEventChannel = Channel<CategoryListOneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    init {
        getCategories()
    }

    fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        categories = result?.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(
                        CategoryListOneTimeEvent.ErrorEvent(
                            result?.message ?: "An unexpected error occured"
                        )
                    )
                    _state.value = _state.value.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}