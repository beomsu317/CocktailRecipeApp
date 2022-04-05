package com.beomsu317.cocktailrecipeapp.presentation.cocktail_info

sealed class CocktailInfoOneTimeEvent {
    data class ErrorEvent(val error: String): CocktailInfoOneTimeEvent()
}
