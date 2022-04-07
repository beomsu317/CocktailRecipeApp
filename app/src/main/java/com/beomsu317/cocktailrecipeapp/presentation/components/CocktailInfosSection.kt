package com.beomsu317.cocktailrecipeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_info.components.CocktailInfoItem
import com.google.accompanist.pager.*
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CocktailInfoSection(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    onIngredientClick: (String) -> Unit,
    useIndicator: Boolean,
    onLikeClick: (CocktailInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            HorizontalPager(
                count = cocktailInfos.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                CocktailInfoItem(
                    cocktailInfo = cocktailInfos[page],
                    pageOffset = pageOffset,
                    onIngredientClick = onIngredientClick,
                    isLoading = isLoading,
                    modifier = Modifier.fillMaxSize()
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

        if (useIndicator) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp),
            )
        }
    }
}