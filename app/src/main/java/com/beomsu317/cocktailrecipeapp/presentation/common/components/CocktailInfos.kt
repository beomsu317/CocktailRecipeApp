package com.beomsu317.cocktailrecipeapp.presentation.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info.components.CocktailInfoItem
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailInfos(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    onIngredientClick: (String) -> Unit,
    ids: List<Int>,
    onLikeClick: (CocktailInfo) -> Unit,
    useIndicator: Boolean,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPager(
                count = cocktailInfos.size,
                state = pagerState,
                modifier = Modifier
                    .weight(0.95f)
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                CocktailInfoItem(
                    cocktailInfo = cocktailInfos[page],
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