package com.beomsu317.cocktailrecipeapp.presentation.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list.components.CocktailGridListItem

@ExperimentalMaterialApi
@Composable
fun CocktailGridList(
    cocktails: List<Cocktail>,
    isLoading: Boolean,
    ids: List<Int>,
    onCocktailClick: (Cocktail) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(cocktails) { cocktail ->
                CocktailGridListItem(
                    cocktail = cocktail,
                    ids = ids,
                    onCocktailClick = onCocktailClick
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