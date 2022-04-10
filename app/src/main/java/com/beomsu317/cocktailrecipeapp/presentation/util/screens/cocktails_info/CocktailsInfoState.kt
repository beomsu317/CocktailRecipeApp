package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info

import androidx.compose.ui.graphics.Color
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

data class CocktailsInfoState(
    val isLoading: Boolean = false,
    val cocktailInfos: List<CocktailInfo> = emptyList(),
    val ids: List<Int> = emptyList(),
    val single: Boolean = false,
    val dominantColor: Int = 0
)
