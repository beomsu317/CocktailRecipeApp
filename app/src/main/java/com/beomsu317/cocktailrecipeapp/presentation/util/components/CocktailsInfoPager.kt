package com.beomsu317.cocktailrecipeapp.presentation.util.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info.components.CocktailsInfoPagerItem
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailsInfoPager(
    cocktailsInfo: List<CocktailInfo>,
    isLoading: Boolean,
    useIndicator: Boolean,
    ids: List<Int>,
    onIngredientClick: (String) -> Unit,
    onLikeClick: (CocktailInfo) -> Unit,
) {
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPager(
                count = cocktailsInfo.size,
                state = pagerState,
                modifier = Modifier
                    .weight(0.95f)
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                CocktailsInfoPagerItem(
                    cocktailInfo = cocktailsInfo[page],
                    pageOffset = pageOffset,
                    onIngredientClick = onIngredientClick,
                    isLoading = isLoading,
                    ids = ids,
                    onLikeClick = onLikeClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
            if (useIndicator) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.05f)
                )
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}