package com.beomsu317.cocktailrecipeapp.presentation.favortes.favorites_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class FavoritesHomeViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases
) : ViewModel() {

    var state by mutableStateOf(FavoritesHomeState())
        private set

    private val _oneTimeEvent = Channel<OneTimeEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    private var getMyCocktailsInfoJob: Job? = null

    private var getIdsJob: Job? = null

    init {
        getMyCocktails()
        refreshIds()
    }

    private fun getMyCocktails() {
        state = state.copy(isLoading = true)
        getMyCocktailsInfoJob?.cancel()
        getMyCocktailsInfoJob = cocktailUseCases.getMyCocktailsInfoUseCase().onEach {
            state = state.copy(isLoading = false, cocktailsInfo = it)
        }.launchIn(viewModelScope)
    }

    private fun refreshIds() {
        getIdsJob?.cancel()
        getIdsJob = cocktailUseCases.getCocktailInfoIdsUseCase().onEach {
            state = state.copy(ids = it)
        }.launchIn(viewModelScope)
    }
}