package com.beomsu317.cocktailrecipeapp.presentation.util.screens.cocktails_info.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.Coil
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.beomsu317.cocktailrecipeapp.R
import com.beomsu317.cocktailrecipeapp.common.Constants.BASE_URL
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.fade

@ExperimentalMaterialApi
@Composable
fun CocktailsInfoPagerItem(
    cocktailInfo: CocktailInfo,
    pageOffset: Float,
    onIngredientClick: (String) -> Unit,
    isLoading: Boolean,
    ids: List<Int>,
    dominantColor: Int,
    onLikeClick: (CocktailInfo) -> Unit,
    onCalcDominantColor: (Drawable) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colors.background,
                            Color(dominantColor)
                        )
                    )
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 70.dp)
                        .placeholder(
                            visible = isLoading,
                            highlight = PlaceholderHighlight.fade(),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    elevation = 16.dp
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(cocktailInfo.strDrinkThumb)
                            .crossfade(true)
                            .build(),
                        contentDescription = cocktailInfo.strDrink,
                        contentScale = ContentScale.Crop,
                        onSuccess = {
                            onCalcDominantColor(it.result.drawable)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cocktailInfo.strDrink,
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .placeholder(
                                visible = isLoading,
                                highlight = PlaceholderHighlight.fade(),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 48.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                    IconButton(
                        onClick = {
                            onLikeClick(cocktailInfo)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = if (ids.contains(cocktailInfo.idDrink)) {
                                painterResource(id = R.drawable.ic_baseline_favorite_24)
                            } else {
                                painterResource(id = R.drawable.ic_baseline_favorite_border_24)
                            },
                            contentDescription = "ThumbUpIcon",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = cocktailInfo.strInstructions.toString(),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .placeholder(
                            visible = isLoading,
                            highlight = PlaceholderHighlight.fade(),
                            shape = RoundedCornerShape(16.dp)
                        ),
                ) {
                    items(cocktailInfo.ingredients) { ingredient ->
                        IngredientItem(
                            ingredient = ingredient,
                            onIngredientClick = onIngredientClick
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun IngredientItem(
    ingredient: Pair<String, String?>,
    onIngredientClick: (String) -> Unit,
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = 4.dp,
        onClick = {
            onIngredientClick(ingredient.first)
        },
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp),
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${BASE_URL}/images/ingredients/${ingredient.first}-Medium.png")
                    .crossfade(true)
                    .build(),
                contentDescription = ingredient.first,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.fade()
                    ),
                contentScale = ContentScale.Crop,
                onLoading = {
                    isLoading = true
                },
                onSuccess = {
                    isLoading = false
                },
                onError = {
                    isLoading = false
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${ingredient.second ?: ""} ${ingredient.first}",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.fade(),
                    ),
                color = Color.Black
            )
        }
    }
}