package com.beomsu317.cocktailrecipeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@HiltAndroidApp
class CocktailRecipeApp: Application()