package com.beomsu317.cocktailrecipeapp.domain.model

import android.util.Log
import com.beomsu317.cocktailrecipeapp.data.local.entity.CocktailInfoEntity
import kotlinx.serialization.SerialName
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

data class CocktailInfo(
    val dateModified: String?,
    val idDrink: Int,
    val strAlcoholic: String?,
    val strCategory: String?,
    val strDrink: String,
    val strGlass: String?,
    val strDrinkThumb: String?,
    val ingredients: List<Pair<String, String?>>,
    val strInstructions: String?,
    val strImageSource: String?,
)

fun CocktailInfo.toCocktailInfoEntity(): CocktailInfoEntity {
    return CocktailInfoEntity(
        dateModified = dateModified,
        idDrink = idDrink,
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