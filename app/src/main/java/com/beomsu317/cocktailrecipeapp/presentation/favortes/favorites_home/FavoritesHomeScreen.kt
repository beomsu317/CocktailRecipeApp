package com.beomsu317.cocktailrecipeapp.presentation.favortes.favorites_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.model.toCocktail
import com.beomsu317.cocktailrecipeapp.presentation.util.components.CocktailGridList

@ExperimentalMaterialApi
@Composable
fun FavoritesHomeScreen(
    onCocktailClick: (Cocktail) -> Unit,
    viewModel: FavoritesHomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = "Favorites")
            }
        )
        MyCocktailSection(
            isLoading = state.isLoading,
            cocktailsInfo = state.cocktailsInfo,
            ids = state.ids,
            onCocktailClick = onCocktailClick
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun MyCocktailSection(
    cocktailsInfo: List<CocktailInfo>,
    isLoading: Boolean,
    ids: List<Int>,
    onCocktailClick: (Cocktail) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CocktailGridList(
            cocktails = cocktailsInfo.map { it.toCocktail() },
            isLoading = isLoading,
            ids = ids,
            onCocktailClick = onCocktailClick
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}