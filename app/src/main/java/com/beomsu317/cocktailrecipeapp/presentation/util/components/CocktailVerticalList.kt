package com.beomsu317.cocktailrecipeapp.presentation.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo

@ExperimentalMaterialApi
@Composable
fun CocktailVerticalList(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    ids: List<Int>,
    onCocktailClick: (CocktailInfo) -> Unit
) {
    Box(
       modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(cocktailInfos) { cocktailInfo ->
                CocktailVerticalListItem(
                    cocktailInfo = cocktailInfo,
                    ids = ids,
                    onCocktailClick = onCocktailClick,
                )
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}