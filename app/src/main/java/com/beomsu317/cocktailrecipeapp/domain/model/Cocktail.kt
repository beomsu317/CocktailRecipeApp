package com.beomsu317.cocktailrecipeapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: Int,
)
