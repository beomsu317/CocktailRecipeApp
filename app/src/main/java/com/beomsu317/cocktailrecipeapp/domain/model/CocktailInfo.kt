package com.beomsu317.cocktailrecipeapp.domain.model

import android.util.Log
import kotlinx.serialization.SerialName
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

data class CocktailInfo(
    val dateModified: String?,
    val idDrink: String,
    val strAlcoholic: String?,
    val strCategory: String?,
    val strDrink: String,
    val strGlass: String?,
    val strDrinkThumb: String?,
    val ingredients: List<Pair<String, String?>>,
    val strInstructions: String?,
    val strImageSource: String?,
)
