package com.beomsu317.cocktailrecipeapp.domain.model

data class Ingredient(
    val idIngredient: String = "",
    val strIngredient: String = "",
    val strABV: String? = "",
    val strAlcohol: String? = "",
    val strDescription: String? = "",
    val strType: String? = ""
)
