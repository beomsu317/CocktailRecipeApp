package com.beomsu317.cocktailrecipeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailInfoEntity(
    @PrimaryKey val idDrink: Int,
    val dateModified: String?,
    val strAlcoholic: String?,
    val strCategory: String?,
    val strDrink: String,
    val strGlass: String?,
    val strDrinkThumb: String?,
    val ingredients: List<Pair<String, String?>>,
    val strInstructions: String?,
    val strImageSource: String?,
)
