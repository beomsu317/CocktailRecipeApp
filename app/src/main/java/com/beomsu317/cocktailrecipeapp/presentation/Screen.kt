package com.beomsu317.cocktailrecipeapp.presentation

import androidx.annotation.DrawableRes
import com.beomsu317.cocktailrecipeapp.R

sealed class Screen(val route: String, @DrawableRes val resId: Int = -1, val label: String = "") {
    object CategoryScreen: Screen(route = "category_screen", resId = R.drawable.ic_baseline_category_24, label = "Category")
    object CategoryListScreen: Screen(route = "category_list_screen")
    object CocktailListScreen: Screen("cocktail_list_screen")
    object CocktailDetailScreen: Screen("cocktail_detail_screen")
    object IngredientDetailScreen: Screen("ingredient_detail_screen")
    object SearchScreen: Screen(route = "search_screen", resId = R.drawable.ic_baseline_search_24, label = "Search")
    object MyCocktailScreen: Screen("my_cocktail_screen")
}

val bottomNavBarItems = listOf(
    Screen.CategoryScreen,
    Screen.SearchScreen
)