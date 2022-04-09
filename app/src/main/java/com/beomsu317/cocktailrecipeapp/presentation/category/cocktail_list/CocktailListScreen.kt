package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.util.components.CocktailGridList
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun CocktailListScreen(
    category: String,
    scaffoldState: ScaffoldState,
    onBackClick: () -> Unit,
    onCocktailClick: (Cocktail) -> Unit,
    viewModel: CocktailListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = viewModel.oneTimeEventFlow) {
        viewModel.oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is OneTimeEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        oneTimeEvent.message,
                        null,
                        SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
        CocktailListSection(
            cocktails = state.cocktails,
            isLoading = state.isLoading,
            onCocktailClick = onCocktailClick,
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun CocktailListSection(
    cocktails: List<Cocktail>,
    isLoading: Boolean,
    onCocktailClick: (Cocktail) -> Unit
) {
    CocktailGridList(
        cocktails = cocktails,
        isLoading = isLoading,
        onCocktailClick = onCocktailClick
    )
}