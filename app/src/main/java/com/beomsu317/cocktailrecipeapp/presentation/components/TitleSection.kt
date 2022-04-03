package com.beomsu317.cocktailrecipeapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TitleSection(
    title: String
) {
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(32.dp))
}