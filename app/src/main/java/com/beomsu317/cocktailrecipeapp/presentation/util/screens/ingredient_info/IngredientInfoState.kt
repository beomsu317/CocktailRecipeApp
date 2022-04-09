package com.beomsu317.cocktailrecipeapp.presentation.util.screens.ingredient_info

import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient

data class IngredientInfoState(
    val isLoading: Boolean = false,
    val ingredient: Ingredient = Ingredient()
)
