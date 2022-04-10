package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.util.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.util.components.CocktailsInfoPager
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
        CocktailInfosSection(
            cocktailsInfo = state.cocktailInfos,
            isLoading = state.isLoading,
            ids = state.ids,
            single = state.single,
            dominantColor = state.dominantColor,
            onIngredientClick = onIngredientClick,
            onLikeClick = { cocktailInfo ->
                viewModel.onEvent(CocktailsInfoEvent.ToggleCocktailInfo(cocktailInfo))
            },
            onCalcDominantColor = {
                viewModel.onEvent(CocktailsInfoEvent.CalcDominantColor(it))
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
    dominantColor: Int,
    onCalcDominantColor: (Drawable) -> Unit,
    onIngredientClick: (String) -> Unit,
    onLikeClick: (CocktailInfo) -> Unit
) {
    Box(
       modifier = Modifier
           .fillMaxSize(),
    ) {
        CocktailsInfoPager(
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
            dominantColor = dominantColor,
            onIngredientClick = onIngredientClick,
            onLikeClick = onLikeClick,
            onCalcDominantColor = onCalcDominantColor
        )
    }
}
