package com.beomsu317.cocktailrecipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beomsu317.cocktailrecipeapp.domain.model.toCocktail
import com.beomsu317.cocktailrecipeapp.presentation.category.category_list.CategoryListScreen
import com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info.CocktailsInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list.CocktailListScreen
import com.beomsu317.cocktailrecipeapp.presentation.util.screens.ingredient_info.IngredientInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.favortes.favorites_home.FavoritesHomeScreen
import com.beomsu317.cocktailrecipeapp.presentation.search.search_home.SearchHomeScreen
import com.beomsu317.cocktailrecipeapp.presentation.ui.theme.CocktailRecipeAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class CocktailRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailRecipeAppTheme {
                val scaffoldState = rememberScaffoldState()
                val rootNavController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            currentDestination?.hierarchy
                            bottomNavBarItems.forEach { screen ->
                                val isSelected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route }
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(
                                                id = if (isSelected == true) {
                                                    screen.selectedResId
                                                } else {
                                                    screen.unselectedResId
                                                }
                                            ),
                                            contentDescription = screen.label
                                        )
                                    },
                                    selected = isSelected == true,
                                    label = {
                                        Text(
                                            text = screen.label
                                        )
                                    },
                                    alwaysShowLabel = false,
                                    selectedContentColor = MaterialTheme.colors.secondary,
                                    unselectedContentColor = Color.White,
                                    onClick = {
                                        rootNavController.navigate(screen.route) {
                                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = rootNavController,
                        startDestination = BottomNavScreen.CategoryScreen.route,
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    ) {
                        composable(BottomNavScreen.CategoryScreen.route) {
                            val categoryNavController = rememberNavController()
                            NavHost(
                                navController = categoryNavController,
                                startDestination = CategoryScreen.CategoryListScreen.route
                            ) {
                                composable(route = CategoryScreen.CategoryListScreen.route) {
                                    CategoryListScreen(
                                        scaffoldState = scaffoldState,
                                        onCategoryClick = { category ->
                                            categoryNavController.navigate(
                                                route = "${CategoryScreen.CocktailListScreen.route}/${
                                                    category.replace(
                                                        "/",
                                                        "%2f"
                                                    )
                                                }"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${CategoryScreen.CocktailListScreen.route}/{category}",
                                    arguments = listOf(
                                        navArgument("category") {
                                            type = NavType.StringType
                                        }
                                    )
                                ) { navBackStackEntry ->
                                    val category =
                                        navBackStackEntry.arguments?.getString("category")
                                            .toString()
                                    CocktailListScreen(
                                        category = category,
                                        onBackClick = {
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState,
                                        onCocktailClick = { cocktail ->
                                            val encodedCocktail = Json.encodeToString(cocktail)
                                            categoryNavController.navigate(
                                                route = "${CategoryScreen.CocktailsInfoScreen.route}/${
                                                    URLEncoder.encode(encodedCocktail, "UTF-8")
                                                }/${false}"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${CategoryScreen.CocktailsInfoScreen.route}/{cocktail}/{single}",
                                    arguments = listOf(
                                        navArgument("cocktail") {
                                            type = NavType.StringType
                                        },
                                        navArgument("single") {
                                            type = NavType.BoolType
                                        }
                                    )
                                ) { navBackStackEntry ->
                                    CocktailsInfoScreen(
                                        onNavigateUp = {
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState,
                                        onIngredientClick = { ingredient ->
                                            categoryNavController.navigate(
                                                route = "${CategoryScreen.IngredientInfoScreen.route}/${
                                                    URLEncoder.encode(ingredient, "UTF-8")
                                                }"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${CategoryScreen.IngredientInfoScreen.route}/{ingredient}",
                                    arguments = listOf(
                                        navArgument("ingredient") {
                                            type = NavType.StringType
                                        }
                                    )
                                ) { navBackStackEntry ->
                                    IngredientInfoScreen(
                                        onBackClick = {
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState
                                    )
                                }
                            }
                        }
                        composable(BottomNavScreen.SearchScreen.route) {
                            val searchNavController = rememberNavController()
                            NavHost(
                                navController = searchNavController,
                                startDestination = SearchScreen.SearchHomeScreen.route
                            ) {
                                composable(SearchScreen.SearchHomeScreen.route) {
                                    SearchHomeScreen(
                                        scaffoldState = scaffoldState,
                                        onCocktailClick = { cocktailInfo ->
                                            val cocktailInfoStr =
                                                Json.encodeToString(cocktailInfo.toCocktail())
                                            searchNavController.navigate(
                                                route = "${SearchScreen.CocktailInfoScreen.route}/${
                                                    URLEncoder.encode(
                                                        cocktailInfoStr,
                                                        "UTF-8"
                                                    )
                                                }/${true}"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${SearchScreen.CocktailInfoScreen.route}/{cocktail}/{single}",
                                    arguments = listOf(
                                        navArgument("cocktail") {
                                            type = NavType.StringType
                                        },
                                        navArgument("single") {
                                            type = NavType.BoolType
                                        }
                                    )
                                ) {
                                    CocktailsInfoScreen(
                                        scaffoldState = scaffoldState,
                                        onNavigateUp = {
                                            searchNavController.popBackStack()
                                        },
                                        onIngredientClick = { ingredient ->
                                            searchNavController.navigate(
                                                route = "${SearchScreen.IngredientInfoScreen.route}/${
                                                    URLEncoder.encode(
                                                        ingredient,
                                                        "UTF-8"
                                                    )
                                                }"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${SearchScreen.IngredientInfoScreen.route}/{ingredient}",
                                    arguments = listOf(
                                        navArgument("ingredient") {
                                            type = NavType.StringType
                                        }
                                    )
                                ) {
                                    IngredientInfoScreen(
                                        onBackClick = {
                                            searchNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState
                                    )
                                }
                            }
                        }
                        composable(BottomNavScreen.FavoritesScreen.route) {
                            val favoritesNavController = rememberNavController()
                            NavHost(
                                navController = favoritesNavController,
                                startDestination = FavoritesScreen.FavoritesHomeScreen.route
                            ) {
                                composable(route = FavoritesScreen.FavoritesHomeScreen.route) {
                                    FavoritesHomeScreen(
                                        onCocktailClick = { cocktailInfo ->
                                            val encodedCocktail = Json.encodeToString(cocktailInfo)
                                            favoritesNavController.navigate(
                                                route = "${FavoritesScreen.CocktailInfoScreen.route}/${
                                                    URLEncoder.encode(
                                                        encodedCocktail,
                                                        "UTF-8"
                                                    )
                                                }/${true}"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${FavoritesScreen.CocktailInfoScreen.route}/{cocktail}/{single}",
                                    arguments = listOf(
                                        navArgument("cocktail") {
                                            type = NavType.StringType
                                        },
                                        navArgument("single") {
                                            type = NavType.BoolType
                                        }
                                    )
                                ) {
                                    CocktailsInfoScreen(
                                        scaffoldState = scaffoldState,
                                        onNavigateUp = {
                                            favoritesNavController.popBackStack()
                                        },
                                        onIngredientClick = { ingredient ->
                                            favoritesNavController.navigate(
                                                route = "${FavoritesScreen.IngredientInfoScreen.route}/${
                                                    URLEncoder.encode(
                                                        ingredient,
                                                        "UTF-8"
                                                    )
                                                }"
                                            )
                                        }
                                    )
                                }
                                composable(
                                    route = "${FavoritesScreen.IngredientInfoScreen.route}/{ingredient}",
                                    arguments = listOf(
                                        navArgument("ingredient") {
                                            type = NavType.StringType
                                        }
                                    )
                                ) {
                                    IngredientInfoScreen(
                                        onBackClick = {
                                            favoritesNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}