package com.beomsu317.cocktailrecipeapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDto(
    @SerialName("drinks")
    val drinks: List<DrinkCategory>
)

fun CategoriesDto.toCategories(): List<String> {
    return drinks.map { it.strCategory }
}