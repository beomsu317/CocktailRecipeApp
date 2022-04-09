package com.beomsu317.cocktailrecipeapp.presentation.search.cocktail_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient
import com.beomsu317.cocktailrecipeapp.presentation.util.components.CocktailsInfo
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailInfoScreen(
    onNavigateUp: () -> Unit,
    onIngredientClick: (String) -> Unit,
    viewModel: CocktailInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Cocktail Info"
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onNavigateUp()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
        CocktailInfoSection(
            cocktailInfo = state.cocktailInfo,
            isLoading = state.isLoading,
            ids = state.ids,
            onIngredientClick = onIngredientClick,
            onLikeClick = {
                viewModel.onEvent(CocktailInfoEvent.ToggleCocktailInfo(cocktailInfo = state.cocktailInfo))
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CocktailInfoSection(
    cocktailInfo: CocktailInfo,
    isLoading: Boolean,
    ids: List<Int>,
    onIngredientClick: (String) -> Unit,
    onLikeClick: (CocktailInfo) -> Unit
) {
    CocktailsInfo(
        cocktailsInfo = listOf(cocktailInfo),
        isLoading = isLoading,
        onIngredientClick = onIngredientClick,
        ids = ids,
        onLikeClick = onLikeClick,
        useIndicator = false
    )
}