package com.beomsu317.cocktailrecipeapp.presentation.favortes.favorites_home

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class FavoritesHomeState(
    val isLoading: Boolean = false,
    val cocktailsInfo: List<CocktailInfo> = emptyList(),
    val ids: List<Int> = emptyList()
)
