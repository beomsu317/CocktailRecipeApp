package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.util.components.CocktailsInfo
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailsInfoScreen(
    onNavigateUp: () -> Unit,
    scaffoldState: ScaffoldState,
    onIngredientClick: (String) -> Unit,
    viewModel: CocktailsInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val oneTimeEventFlow = viewModel.oneTimeEventFlow

    LaunchedEffect(key1 = oneTimeEventFlow) {
        oneTimeEventFlow.collectLatest { oneTimeEvent ->
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
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Cocktail Info"
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        onNavigateUp()
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
        Spacer(modifier = Modifier.height(16.dp))
        CocktailInfosSection(
            cocktailsInfo = state.cocktailInfos,
            isLoading = state.isLoading,
            ids = state.ids,
            single = state.single,
            onIngredientClick = onIngredientClick,
            onLikeClick = { cocktailInfo ->
                viewModel.onEvent(CocktailsInfoEvent.ToggleCocktailInfo(cocktailInfo))
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CocktailInfosSection(
    cocktailsInfo: List<CocktailInfo>,
    isLoading: Boolean,
    ids: List<Int>,
    single: Boolean,
    onIngredientClick: (String) -> Unit,
    onLikeClick: (CocktailInfo) -> Unit
) {
    CocktailsInfo(
        cocktailsInfo = if (single) {
            if (cocktailsInfo.isNotEmpty()) {
                listOf(cocktailsInfo.first())
            } else {
                emptyList()
            }
        } else {
            cocktailsInfo
        },
        isLoading = isLoading,
        ids = ids,
        useIndicator = if (single) {
            false
        } else {
            true
        },
        onIngredientClick = onIngredientClick,
        onLikeClick = onLikeClick
    )
}
