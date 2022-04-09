package com.beomsu317.cocktailrecipeapp.presentation.search.search_home

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class SearchHomeState(
    val cocktails: List<CocktailInfo> = emptyList(),
    val isLoading: Boolean = false,
    val name: String = "",
    val ids: List<Int> = emptyList()
)
