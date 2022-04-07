package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun CocktailListTopBar(
    category: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = category) },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}