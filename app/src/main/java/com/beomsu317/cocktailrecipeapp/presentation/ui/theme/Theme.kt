package com.beomsu317.cocktailrecipeapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Color(0xff870d4c),
    primaryVariant = Color(0xff550024),
    onPrimary = Color(0xffffffff),
    secondary = Color(0xffff80ab),
    onSecondary = Color(0xff000000),
    background = Color.White,
    onBackground = Color(0xff870d4d),
//    surface = LightGray,
//    onSurface = TextWhite,
)

@Composable
fun CocktailRecipeAppTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = colors.primaryVariant)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}