package com.beomsu317.cocktailrecipeapp.presentation.search.cocktail_info

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.use_case.CocktailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    var state by mutableStateOf(CocktailInfoState())
        private set

    private var getIdsJob: Job? = null

    init {
        state = state.copy(isLoading = true)
        val encodedCocktailInfo = savedStateHandle.get<String>("cocktailInfo")
        val decodedCocktailInfo = URLDecoder.decode(encodedCocktailInfo, "UTF-8")
        val cocktailInfo = Json.decodeFromString<CocktailInfo>(decodedCocktailInfo)
        state = state.copy(cocktailInfo = cocktailInfo, isLoading = false)
        refreshIds()
    }

    fun onEvent(event: CocktailInfoEvent) {
        when (event) {
            is CocktailInfoEvent.ToggleCocktailInfo -> {
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
}