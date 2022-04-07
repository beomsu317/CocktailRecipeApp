package com.beomsu317.cocktailrecipeapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetCocktailInfosByNameUseCase
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
class SearchViewModel @Inject constructor(
    private val getCocktailInfosByNameUseCase: GetCocktailInfosByNameUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                getCocktails(event.name)
            }
        }
    }

    private fun getCocktails(name: String) {
        _state.value = _state.value.copy(cocktails = emptyList(), isLoading = true, name = name)

        if (name.isEmpty()) {
            viewModelScope.launch {
                _oneTimeEventChannel.send(OneTimeEvent.Error("String is empty"))
            }
            return
        }

        getCocktailInfosByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        cocktails = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(
                        OneTimeEvent.Error(
                            result.message ?: "An unexpected error occured"
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