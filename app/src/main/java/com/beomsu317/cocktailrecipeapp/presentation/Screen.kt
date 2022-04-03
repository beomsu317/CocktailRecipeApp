package com.beomsu317.cocktailrecipeapp.presentation

sealed class Screen(val route: String) {
    object CategoryListScreen: Screen("category_list_screen")
    object CocktailListScreen: Screen("cocktail_list_screen")
    object SearchScreen: Screen("search_screen")
    object RandomScreen: Screen("random_screen")
    object MyCocktailScreen: Screen("my_cocktail_screen")
}
