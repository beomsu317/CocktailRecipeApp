package com.beomsu317.cocktailrecipeapp.presentation.search.search_home

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

sealed class SearchHomeEvent {
    data class Search(val name: String): SearchHomeEvent()
    data class ToggleCocktailInfo(val cocktailInfo: CocktailInfo): SearchHomeEvent()
}
