package com.beomsu317.cocktailrecipeapp.presentation.cocktail_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_list.components.CocktailItem
import com.beomsu317.cocktailrecipeapp.presentation.components.TitleSection
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CocktailListScreen(
    category: String,
    scaffoldState: ScaffoldState,
    viewModel: CocktailListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = viewModel.oneTimeEventFlow) {
        viewModel.oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is CocktailListOneTimeEvent.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(oneTimeEvent.error, null, SnackbarDuration.Short)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleSection(title = category)
        CocktailSection(
            cocktails = state.cocktails,
            isLoading = state.isLoading
        )
    }
}

@Composable
fun CocktailSection(
    cocktails: List<Cocktail>,
    isLoading: Boolean
) {
    Box(
       modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(cocktails) {
                CocktailItem(it)
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