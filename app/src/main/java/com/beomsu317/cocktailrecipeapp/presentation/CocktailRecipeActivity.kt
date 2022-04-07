package com.beomsu317.cocktailrecipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
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
import com.beomsu317.cocktailrecipeapp.presentation.category.category_list.CategoryListScreen
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info.CocktailInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list.CocktailListScreen
import com.beomsu317.cocktailrecipeapp.presentation.category.ingredient_detail.IngredientInfoScreen
import com.beomsu317.cocktailrecipeapp.presentation.search.SearchScreen
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
                                            painter = painterResource(id = screen.resId),
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
                ) {
                    innerPadding ->
                    NavHost(
                        navController = rootNavController,
                        startDestination = Screen.SearchScreen.route,
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    ) {
                        composable(Screen.CategoryScreen.route) {
                            val categoryNavController = rememberNavController()
                            NavHost(
                                navController = categoryNavController,
                                startDestination = Screen.CategoryListScreen.route
                            ) {
                                composable(route = Screen.CategoryListScreen.route) {
                                    CategoryListScreen(
                                        scaffoldState = scaffoldState,
                                        onCategoryClick = { category ->
                                            categoryNavController.navigate(
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
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState,
                                        onCocktailClick = { cocktail ->
                                            val encodedCocktail = Json.encodeToString(
                                                cocktail
                                            )
                                            categoryNavController.navigate(
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
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState,
                                        onIngredientClick = { ingredient ->
                                            categoryNavController.navigate("${Screen.IngredientDetailScreen.route}/${ingredient}")
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
                                            categoryNavController.popBackStack()
                                        },
                                        scaffoldState = scaffoldState
                                    )
                                }
                            }
                        }
                        composable(Screen.SearchScreen.route) {
                            SearchScreen(scaffoldState = scaffoldState)
                        }
                    }
                }
            }
        }
    }
}