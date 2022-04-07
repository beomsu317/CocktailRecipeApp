package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info.components.CocktailInfoTopBar
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.components.CocktailInfoSection
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
                is OneTimeEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(oneTimeEvent.message, null, SnackbarDuration.Short)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CocktailInfoTopBar(
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        CocktailInfoSection(
            cocktailInfos = state.cocktailInfos,
            isLoading = state.isLoading,
            onIngredientClick = onIngredientClick,
            true,
            onLikeClick = {

            }
        )
    }
}

