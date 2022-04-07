package com.beomsu317.cocktailrecipeapp.presentation.search

import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail

data class SearchState(
    val cocktails: List<Cocktail> = emptyList(),
    val isLoading: Boolean = false
)
