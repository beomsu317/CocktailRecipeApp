package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list

import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail

data class CocktailListState(
    val cocktails: List<Cocktail> = emptyList(),
    val isLoading: Boolean = false
)
