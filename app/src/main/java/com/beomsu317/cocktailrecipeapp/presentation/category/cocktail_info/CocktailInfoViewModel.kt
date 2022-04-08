package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetCocktailInfosByNameUseCase
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class CocktailInfoViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CocktailInfoState())
    val state: State<CocktailInfoState> = _state

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    private var getIdsJob: Job? = null

    init {
        savedStateHandle.get<String>("cocktail")?.let { encodedCocktail ->

            val decodedCocktail =
                Json.decodeFromString<Cocktail>(URLDecoder.decode(encodedCocktail, "UTF-8"))
            getCocktailInfos(decodedCocktail.strDrink)
        }
        refreshIds()
    }

    fun onEvent(event: CocktailInfoEvent) {
        when (event) {
            is CocktailInfoEvent.ToggleCocktailInfo -> {
                viewModelScope.launch {
                    if (_state.value.ids.contains(event.cocktailInfo.idDrink)) {
                        cocktailUseCases.deleteCocktailInfoByIdUseCase(event.cocktailInfo.idDrink)
                    } else {
                        cocktailUseCases.insertCocktailInfoUseCase(event.cocktailInfo)
                    }
                    refreshIds()
                }
            }
        }
    }

    private fun refreshIds() {
        getIdsJob?.cancel()
        getIdsJob = cocktailUseCases.getCocktailInfoIdsUseCase().onEach { ids ->
            _state.value = _state.value.copy(ids = ids)
        }.launchIn(viewModelScope)
    }

    private fun getCocktailInfos(name: String) {
        cocktailUseCases.getCocktailInfosByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        cocktailInfos = result?.data ?: emptyList()
                    )
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