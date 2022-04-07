package com.beomsu317.cocktailrecipeapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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