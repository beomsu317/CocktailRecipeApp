package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

sealed interface CocktailInfoEvent {
    data class ToggleCocktailInfo(val cocktailInfo: CocktailInfo): CocktailInfoEvent
}