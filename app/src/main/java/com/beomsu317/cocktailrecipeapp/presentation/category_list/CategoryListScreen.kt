package com.beomsu317.cocktailrecipeapp.presentation.category_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.presentation.category_list.components.CategoryListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CategoryListScreen(
    scaffoldState: ScaffoldState,
    viewModel: CategoryListViewModel = hiltViewModel(),
    onCategoryClick: () -> Unit
) {
    val state = viewModel.state.value
    val oneTimeEventFlow = viewModel.oneTimeEventFlow
    val swipeRefreshState = rememberSwipeRefreshState(state.isLoading)

    LaunchedEffect(key1 = oneTimeEventFlow) {
        viewModel.oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is CategoryListOneTimeEvent.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = oneTimeEvent.error,
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
        Spacer(modifier = Modifier.height(32.dp))
        TitleSection()
        Spacer(modifier = Modifier.height(32.dp))
        CategotySection(
            swipeRefreshState = swipeRefreshState,
            onCategoryClick = onCategoryClick,
            onRefresh = {
                viewModel.getCategories()
            },
            categories = state.categories
        )
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Select Category",
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CategotySection(
    swipeRefreshState: SwipeRefreshState,
    onCategoryClick: () -> Unit,
    onRefresh: () -> Unit,
    categories: List<String>
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            onRefresh()
        },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
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
        }
    }
}