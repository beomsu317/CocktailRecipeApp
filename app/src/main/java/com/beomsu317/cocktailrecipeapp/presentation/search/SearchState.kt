package com.beomsu317.cocktailrecipeapp.presentation.search

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class SearchState(
    val cocktails: List<CocktailInfo> = emptyList(),
    val isLoading: Boolean = false,
    val name: String = ""
)
