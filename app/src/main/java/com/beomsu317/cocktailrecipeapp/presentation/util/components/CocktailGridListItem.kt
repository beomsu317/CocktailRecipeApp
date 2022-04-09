package com.beomsu317.cocktailrecipeapp.presentation.category.cocktail_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.beomsu317.cocktailrecipeapp.R
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.fade

@ExperimentalMaterialApi
@Composable
fun CocktailGridListItem(
    cocktail: Cocktail,
    ids: List<Int>,
    onCocktailClick: (Cocktail) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = {
            onCocktailClick(cocktail)
        },
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(cocktail.strDrinkThumb)
                    .crossfade(true)
                    .build(),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.fade()
                            )
                    )
                },
                contentDescription = cocktail.strDrink,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                Color.Black
                            ),
                            startY = size.height / 2f,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
            )

            if (ids.contains(cocktail.idDrink)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = MaterialTheme.colors.primary,
                )
            }

            Text(
                text = cocktail.strDrink,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .align(Alignment.BottomCenter),
                color = Color.White
            )
        }
    }

}