package com.beomsu317.cocktailrecipeapp.presentation.ingredient_detail

sealed class IngredientInfoOneTimeEvent {
    data class ErrorEvent(val error: String): IngredientInfoOneTimeEvent()
}