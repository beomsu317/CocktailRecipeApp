package com.beomsu317.cocktailrecipeapp.presentation.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchTopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "Search") },
        backgroundColor = MaterialTheme.colors.primary,
    )
}