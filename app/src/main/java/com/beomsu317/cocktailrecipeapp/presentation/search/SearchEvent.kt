package com.beomsu317.cocktailrecipeapp.presentation.search

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

sealed class SearchEvent {
    data class Search(val name: String): SearchEvent()
    data class ToggleCocktailInfo(val cocktailInfo: CocktailInfo): SearchEvent()
}
