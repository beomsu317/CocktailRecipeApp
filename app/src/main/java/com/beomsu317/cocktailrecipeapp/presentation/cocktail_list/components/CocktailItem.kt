package com.beomsu317.cocktailrecipeapp.presentation.cocktail_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.presentation.components.ShimmerAnimation

@ExperimentalMaterialApi
@Composable
fun CocktailItem(
    cocktail: Cocktail,
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
        }
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
                    ShimmerAnimation(
                        { brush ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(brush = brush)
                            )
                        }
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
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
            )
            Text(
                text = cocktail.strDrink,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp ,bottom = 8.dp)
                    .align(Alignment.BottomCenter),
                color = Color.White
            )
        }
    }

}