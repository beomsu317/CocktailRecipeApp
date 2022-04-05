package com.beomsu317.cocktailrecipeapp.presentation.cocktail_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.beomsu317.cocktailrecipeapp.common.Constants.BASE_URL
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.components.ShimmerAnimation

@ExperimentalMaterialApi
@Composable
fun CocktailInfoItem(
    cocktailInfo: CocktailInfo,
    pageOffset: Float,
    onIngredientClick: (String) -> Unit
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
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cocktailInfo.strDrinkThumb)
                .crossfade(true)
                .build(),
            contentDescription = cocktailInfo.strDrink,
            modifier = Modifier
                .weight(0.4f)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = cocktailInfo.strDrink,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
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

@ExperimentalMaterialApi
@Composable
fun IngredientItem(
    ingredient: Pair<String, String?>,
    onIngredientClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = 4.dp,
        onClick = {
            onIngredientClick(ingredient.first)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .height(150.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${BASE_URL}/images/ingredients/${ingredient.first}-Medium.png")
                    .crossfade(true)
                    .build(),
                contentDescription = ingredient.first,
                modifier = Modifier,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onBackground
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${ingredient.second ?: ""} ${ingredient.first}",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    }
}