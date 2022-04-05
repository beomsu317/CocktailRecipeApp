package com.beomsu317.cocktailrecipeapp.data.remote.dto


import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    @SerialName("idIngredient")
    val idIngredient: String,
    @SerialName("strABV")
    val strABV: String?,
    @SerialName("strAlcohol")
    val strAlcohol: String?,
    @SerialName("strDescription")
    val strDescription: String?,
    @SerialName("strIngredient")
    val strIngredient: String,
    @SerialName("strType")
    val strType: String?
)

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        idIngredient = idIngredient,
        strABV = strABV,
        strAlcohol = strAlcohol,
        strDescription = strDescription,
        strIngredient = strIngredient,
        strType = strType
    )
}