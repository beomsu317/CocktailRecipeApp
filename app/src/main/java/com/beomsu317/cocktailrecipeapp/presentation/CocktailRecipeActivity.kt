package com.beomsu317.cocktailrecipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beomsu317.cocktailrecipeapp.presentation.category_list.CategoryListScreen
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_list.CocktailListScreen
import com.beomsu317.cocktailrecipeapp.presentation.ui.theme.CocktailRecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
                                onCategoryClick = {
                                    navController.navigate(Screen.CocktailListScreen.route)
                                }
                            )
                        }
                        composable(Screen.SearchScreen.route) {

                        }
                        composable(Screen.RandomScreen.route) {

                        }
                        composable(Screen.CocktailListScreen.route) {
                            CocktailListScreen()
                        }
                    }
                }
            }
        }
    }
}