package com.beomsu317.cocktailrecipeapp.presentation.favortes

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
class FavoritesViewModel @Inject constructor(
    private val cocktailUseCases: CocktailUseCases
) : ViewModel() {

    var state by mutableStateOf(FavoritesState())
        private set

    private val _oneTimeEvent = Channel<OneTimeEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    private var getMyCocktailsInfoJob: Job? = null

    init {
        getMyCocktails()
    }

    private fun getMyCocktails() {
        state = state.copy(isLoading = true)
        getMyCocktailsInfoJob?.cancel()
        getMyCocktailsInfoJob = cocktailUseCases.getMyCocktailsInfoUseCase().onEach {
            state = state.copy(isLoading = false, cocktailInfos = it)
        }.launchIn(viewModelScope)
    }
}