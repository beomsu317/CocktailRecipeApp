package com.beomsu317.cocktailrecipeapp.presentation.util.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.beomsu317.cocktailrecipeapp.R
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder

@ExperimentalMaterialApi
@Composable
fun CocktailVerticalListItem(
    cocktailInfo: CocktailInfo,
    ids: List<Int>,
    onCocktailClick: (CocktailInfo) -> Unit
) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        onClick = {
            onCocktailClick(cocktailInfo)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cocktailInfo.strDrinkThumb)
                        .crossfade(true)
                        .build(),
                    contentDescription = cocktailInfo.strDrink,
                    contentScale = Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        ),
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
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = isLoading,
                            highlight = PlaceholderHighlight.fade()
                        )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .weight(0.7f)
            ) {
                val scrollState = rememberScrollState()
                Row(
                   modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cocktailInfo.strDrink,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                    if (ids.contains(cocktailInfo.idDrink)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colors.primary,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = cocktailInfo.ingredients.joinToString {
                        it.first
                    },
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.verticalScroll(scrollState)
                )
            }
        }
    }
}