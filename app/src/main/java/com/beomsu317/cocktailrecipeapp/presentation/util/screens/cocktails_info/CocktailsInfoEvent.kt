package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

sealed interface CocktailsInfoEvent {
    data class ToggleCocktailInfo(val cocktailInfo: CocktailInfo): CocktailsInfoEvent
}