package com.beomsu317.cocktailrecipeapp.presentation.components.ingredient_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient
import com.beomsu317.cocktailrecipeapp.domain.use_case.GetIngredientByNameUseCase
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class IngredientInfoViewModel @Inject constructor(
    private val getIngredientUseCase: GetIngredientByNameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(IngredientInfoState())
    val state: State<IngredientInfoState> = _state

    private val _oneTimeEventChannel = Channel<OneTimeEvent>()
    val oneTimeEventFlow = _oneTimeEventChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("ingredient")?.let {
            getIngredient(it)
        }
    }

    private fun getIngredient(name: String) {
        getIngredientUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        ingredient = result.data ?: Ingredient()
                    )
                }
                is Resource.Error -> {
                    _oneTimeEventChannel.send(OneTimeEvent.Error(result.message ?: "An Unexpected error occured"))
                    _state.value = _state.value.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}