package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CocktailListState())
        private set

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    private var getIdsJob: Job? = null

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            getCocktails(category = category)
        }
        refreshIds()
    }

    fun getCocktails(category: String) {
        cocktailUseCases.getCocktailsByCategoryUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        cocktails = result?.data ?: emptyList(),
                        isLoading = false
                    )
                    Log.d("COCKTAILVIEWMODEL", "getCocktails: ${result?.data!!}")
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

    private fun refreshIds() {
        getIdsJob?.cancel()
        getIdsJob = cocktailUseCases.getCocktailInfoIdsUseCase().onEach {
            state = state.copy(ids = it)
        }.launchIn(viewModelScope)
    }
}