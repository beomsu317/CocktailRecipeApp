package com.beomsu317.cocktailrecipeapp.presentation.util.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun CocktailTopBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        }
    )
}