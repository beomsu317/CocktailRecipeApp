package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info

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
fun CocktailInfoScreen(
    onBackClick: () -> Unit,
    scaffoldState: ScaffoldState,
    onIngredientClick: (String) -> Unit,
    viewModel: CocktailInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val oneTimeEventFlow = viewModel.oneTimeEventFlow

    LaunchedEffect(key1 = oneTimeEventFlow) {
        oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is OneTimeEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(oneTimeEvent.message, null, SnackbarDuration.Short)
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
        Spacer(modifier = Modifier.height(16.dp))
        CocktailInfosSection(
            cocktailInfos = state.cocktailInfos,
            isLoading = state.isLoading,
            ids = state.ids,
            onIngredientClick = onIngredientClick,
            onLikeClick = { cocktailInfo ->
                viewModel.onEvent(CocktailInfoEvent.ToggleCocktailInfo(cocktailInfo))
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CocktailInfosSection(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    ids: List<Int>,
    onIngredientClick: (String) -> Unit,
    onLikeClick: (CocktailInfo) -> Unit
) {
    CocktailsInfo(
        cocktailsInfo = cocktailInfos,
        isLoading = isLoading,
        onIngredientClick = onIngredientClick,
        ids = ids,
        useIndicator = true,
        onLikeClick = onLikeClick
    )
}
