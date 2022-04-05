package com.beomsu317.cocktailrecipeapp.presentation.cocktail_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_info.components.CocktailInfoItem
import com.beomsu317.cocktailrecipeapp.presentation.cocktail_info.components.CocktailInfoTopBar
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

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
                is CocktailInfoOneTimeEvent.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(oneTimeEvent.error, null, SnackbarDuration.Short)
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
            onIngredientClick = onIngredientClick
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailInfoSection(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    onIngredientClick: (String) -> Unit
) {
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                count = cocktailInfos.size,
                state = pagerState,
                modifier = Modifier.weight(0.95f)
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                CocktailInfoItem(
                    cocktailInfo = cocktailInfos[page],
                    pageOffset = pageOffset,
                    onIngredientClick = onIngredientClick
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .weight(0.05f)
                    .align(Alignment.CenterHorizontally),
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onBackground,
            )
        }
    }
}