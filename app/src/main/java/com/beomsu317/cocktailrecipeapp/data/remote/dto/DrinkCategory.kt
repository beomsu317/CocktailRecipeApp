package com.beomsu317.cocktailrecipeapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkCategory(
    @SerialName("strCategory")
    val strCategory: String
)