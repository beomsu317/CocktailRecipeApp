package com.beomsu317.cocktailrecipeapp.presentation.category.category_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.presentation.category.category_list.components.CategoryListItem
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CategoryListScreen(
    scaffoldState: ScaffoldState,
    viewModel: CategoryListViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit
) {
    val state = viewModel.state.value
    val oneTimeEventFlow = viewModel.oneTimeEventFlow

    LaunchedEffect(key1 = oneTimeEventFlow) {
        viewModel.oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is OneTimeEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = oneTimeEvent.message,
                        actionLabel = null,
                        SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryListTopBar()
        Spacer(modifier = Modifier.height(16.dp))
        CategotySection(
            onCategoryClick = onCategoryClick,
            categories = state.categories,
            isLoading = state.isLoading
        )
    }
}


@Composable
fun CategotySection(
    onCategoryClick: (String) -> Unit,
    categories: List<String>,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(categories) {
                CategoryListItem(it, onCategoryClick)
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