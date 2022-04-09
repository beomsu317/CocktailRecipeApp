package com.beomsu317.cocktailrecipeapp.presentation.search.cocktail_info

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class CocktailInfoState(
    val cocktailInfo: CocktailInfo = CocktailInfo(),
    val isLoading: Boolean = false,
    val ids: List<Int> = emptyList()
)
