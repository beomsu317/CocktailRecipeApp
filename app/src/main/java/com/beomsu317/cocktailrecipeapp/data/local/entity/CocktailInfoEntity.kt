package com.beomsu317.cocktailrecipeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

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

fun CocktailInfoEntity.toCocktailInfo(): CocktailInfo {
    return CocktailInfo(
        idDrink = idDrink,
        dateModified = dateModified,
        strAlcoholic = strAlcoholic,
        strCategory = strCategory,
        strDrink = strDrink,
        strGlass = strGlass,
        strDrinkThumb = strDrinkThumb,
        ingredients = ingredients,
        strInstructions = strInstructions,
        strImageSource = strImageSource
    )
}