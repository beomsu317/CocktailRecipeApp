package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetCocktailsByCategoryUseCase
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CocktailListState())
    val state: State<CocktailListState> = _state

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            getCocktails(category = category)
        }
    }

    fun getCocktails(category: String) {
        cocktailUseCases.getCocktailsByCategoryUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        cocktails = result?.data ?: emptyList(),
                        isLoading = false
                    )
                    Log.d("COCKTAILVIEWMODEL", "getCocktails: ${result?.data!!}")
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(
                        OneTimeEvent.Error(
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