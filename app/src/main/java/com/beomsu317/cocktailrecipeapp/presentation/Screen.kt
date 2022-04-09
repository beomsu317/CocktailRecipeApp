package com.beomsu317.cocktailrecipeapp.presentation

import androidx.annotation.DrawableRes
import com.beomsu317.cocktailrecipeapp.R

sealed class CategoryScreen(val route: String) {
    object CategoryListScreen : CategoryScreen("category_list_screen")
    object CocktailListScreen : CategoryScreen("cocktail_list_screen")
    object CocktailsInfoScreen : CategoryScreen("cocktails_info_screen")
    object IngredientInfoScreen : CategoryScreen("ingredient_info_screen")
}

sealed class SearchScreen(val route: String) {
    object SearchHomeScreen : SearchScreen("home_screen")
    object CocktailInfoScreen : SearchScreen("cocktail_info_screen")
    object IngredientInfoScreen : SearchScreen("ingredient_info_screen")
}

sealed class FavoritesScreen(val route: String) {
    object FavoritesHomeScreen : FavoritesScreen("favorites_home_screen")
    object CocktailInfoScreen : FavoritesScreen("cocktail_info_screen")
    object IngredientInfoScreen : FavoritesScreen("ingredient_info_screen")
}

sealed class BottomNavScreen(
    val route: String,
    @DrawableRes val selectedResId: Int,
    @DrawableRes val unselectedResId: Int,
    val label: String = ""
) {
    object CategoryScreen : BottomNavScreen(
        route = "category_screen",
        selectedResId = R.drawable.ic_baseline_category_24,
        unselectedResId = R.drawable.ic_baseline_category_24,
        label = "Category"
    )

    object SearchScreen : BottomNavScreen(
        route = "search_screen",
        selectedResId = R.drawable.ic_baseline_search_24,
        unselectedResId = R.drawable.ic_baseline_search_24,
        label = "Search"
    )

    object FavoritesScreen : BottomNavScreen(
        route = "favorites_screen",
        selectedResId = R.drawable.ic_baseline_favorite_24,
        unselectedResId = R.drawable.ic_baseline_favorite_border_24,
        label = "Favorites"
    )
}

val bottomNavBarItems = listOf(
    BottomNavScreen.CategoryScreen,
    BottomNavScreen.SearchScreen,
    BottomNavScreen.FavoritesScreen
)