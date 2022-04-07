package com.beomsu317.cocktailrecipeapp.presentation.category.category_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryListTopBar() {
    TopAppBar(
        title = { Text(text = "Category") },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
    )
}