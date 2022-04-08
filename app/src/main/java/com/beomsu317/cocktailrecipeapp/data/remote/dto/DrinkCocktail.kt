package com.beomsu317.cocktailrecipeapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkCocktail(
    @SerialName("idDrink")
    val idDrink: Int,
    @SerialName("strDrink")
    val strDrink: String,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String
)