package com.beomsu317.cocktailrecipeapp.presentation.favortes

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class FavoritesState(
    val isLoading: Boolean = false,
    val cocktailInfos: List<CocktailInfo> = emptyList()
)
