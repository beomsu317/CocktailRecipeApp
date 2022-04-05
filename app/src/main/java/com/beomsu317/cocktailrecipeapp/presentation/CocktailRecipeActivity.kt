package com.beomsu317.cocktailrecipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beomsu317.cocktailrecipeapp.presentation.category_list.CategoryListScreen
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_info.CocktailInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_list.CocktailListScreen
import com.beomsu317.cocktailrecipeapp.presentation.ingredient_detail.IngredientInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.ui.theme.CocktailRecipeAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalPagerApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class CocktailRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailRecipeAppTheme {
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    scaffoldState = scaffoldState
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CategoryListScreen.route
                    ) {
                        composable(Screen.CategoryListScreen.route) {
                            CategoryListScreen(
                                scaffoldState = scaffoldState,
                                onCategoryClick = { category ->
                                    navController.navigate(
                                        "${Screen.CocktailListScreen.route}/${
                                            category.replace(
                                                "/",
                                                "%2f"
                                            )
                                        }"
                                    )
                                }
                            )
                        }
                        composable(Screen.SearchScreen.route) {

                        }
                        composable(Screen.RandomScreen.route) {

                        }
                        composable(
                            route = "${Screen.CocktailListScreen.route}/{category}",
                            arguments = listOf(
                                navArgument("category") {
                                    type = NavType.StringType
                                }
                            )
                        ) { navBackStackEntry ->
                            val category =
                                navBackStackEntry.arguments?.getString("category").toString()
                            CocktailListScreen(
                                category = category,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                scaffoldState = scaffoldState,
                                onCocktailClick = { cocktail ->
                                    val encodedCocktail = Json.encodeToString(
                                        cocktail
                                    )
                                    navController.navigate(
                                        "${Screen.CocktailDetailScreen.route}/${
                                            encodedCocktail.replace(
                                                "/",
                                                "%2f"
                                            )
                                        }"
                                    )
                                }
                            )
                        }
                        composable(
                            route = "${Screen.CocktailDetailScreen.route}/{cocktail}",
                            arguments = listOf(
                                navArgument("cocktail") {
                                    type = NavType.StringType
                                }
                            )
                        ) { navBackStackEntry ->
                            CocktailInfoScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                scaffoldState = scaffoldState,
                                onIngredientClick = { ingredient ->
                                    navController.navigate("${Screen.IngredientDetailScreen.route}/${ingredient}")
                                }
                            )
                        }
                        composable(
                            route = "${Screen.IngredientDetailScreen.route}/{ingredient}",
                            arguments = listOf(
                                navArgument("ingredient") {
                                    type = NavType.StringType
                                }
                            )
                        ) { navBackStackEntry ->
                            IngredientInfoScreen(
                                onBackClick = {
                                    navController.popBackStack()
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