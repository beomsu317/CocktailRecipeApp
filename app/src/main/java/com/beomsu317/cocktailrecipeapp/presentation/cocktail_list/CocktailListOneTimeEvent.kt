package com.beomsu317.cocktailrecipeapp.presentation.cocktail_list

sealed class CocktailListOneTimeEvent {
    data class ErrorEvent(val error: String): CocktailListOneTimeEvent()
}
