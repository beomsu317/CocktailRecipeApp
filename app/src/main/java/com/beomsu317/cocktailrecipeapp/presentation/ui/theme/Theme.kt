package com.beomsu317.cocktailrecipeapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
//    primary = BrightGreen,
//    primaryVariant = DarkGreen,
//    secondary = Orange,
//    background = MediumGray,
//    onBackground = TextWhite,
//    surface = LightGray,
//    onSurface = TextWhite,
//    onPrimary = Color.White,
//    onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
//    primary = BrightGreen,
//    primaryVariant = DarkGreen,
//    secondary = Orange,
//    background = MediumGray,
//    onBackground = TextWhite,
//    surface = LightGray,
//    onSurface = TextWhite,
//    onPrimary = Color.White,
//    onSecondary = Color.White,
)

@Composable
fun CocktailRecipeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}