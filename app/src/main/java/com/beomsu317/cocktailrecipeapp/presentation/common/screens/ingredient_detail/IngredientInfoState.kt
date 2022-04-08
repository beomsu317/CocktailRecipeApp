package com.beomsu317.cocktailrecipeapp.presentation.common.screens.ingredient_detail

import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient

data class IngredientInfoState(
    val isLoading: Boolean = false,
    val ingredient: Ingredient = Ingredient()
)
