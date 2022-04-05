package com.beomsu317.cocktailrecipeapp.presentation.cocktail_info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetCocktailInfosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class CocktailInfoViewModel @Inject constructor(
    private val getCocktailInfosUseCase: GetCocktailInfosUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CocktailInfoState())
    val state: State<CocktailInfoState> = _state

    private val _oneTimeEventChannel = Channel<CocktailInfoOneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("cocktail")?.let { encodedCocktail ->
            val decodedCocktail = Json.decodeFromString<Cocktail>(encodedCocktail)
            getCocktailInfos(decodedCocktail.strDrink)
        }
    }

    private fun getCocktailInfos(name: String) {
        getCocktailInfosUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        cocktailInfos = result?.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(CocktailInfoOneTimeEvent.ErrorEvent(result?.message ?: "An unexpected error occured"))
                    _state.value = _state.value.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}