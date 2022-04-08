package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class CocktailInfoState(
    val isLoading: Boolean = false,
    val cocktailInfos: List<CocktailInfo> = emptyList(),
    val ids: List<Int> = emptyList()
)
