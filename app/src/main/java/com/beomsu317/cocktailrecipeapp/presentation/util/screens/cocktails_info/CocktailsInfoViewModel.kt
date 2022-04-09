package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
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
class CocktailsInfoViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CocktailsInfoState())
        private set

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    private var getIdsJob: Job? = null

    init {
        savedStateHandle.get<String>("cocktail")?.let { encodedCocktail ->
            val decodedCocktail =
                Json.decodeFromString<Cocktail>(URLDecoder.decode(encodedCocktail, "UTF-8"))
            getCocktailInfos(decodedCocktail.strDrink)
        }
        savedStateHandle.get<Boolean>("single")?.let { single ->
            state = state.copy(single = single)
        }
        refreshIds()
    }

    fun onEvent(event: CocktailsInfoEvent) {
        when (event) {
            is CocktailsInfoEvent.ToggleCocktailInfo -> {
                viewModelScope.launch {
                    if (state.ids.contains(event.cocktailInfo.idDrink)) {
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
            state = state.copy(ids = ids)
        }.launchIn(viewModelScope)
    }

    private fun getCocktailInfos(name: String) {
        cocktailUseCases.getCocktailInfosByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        cocktailInfos = result?.data ?: emptyList()
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