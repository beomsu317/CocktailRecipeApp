package com.beomsu317.cocktailrecipeapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beomsu317.cocktailrecipeapp.R
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.search.components.SearchTopBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val oneTimeEventFlow = viewModel.oneTimeEventFlow
    LaunchedEffect(key1 = oneTimeEventFlow) {
        oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is OneTimeEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(oneTimeEvent.message, null, SnackbarDuration.Short)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTopBar()
        SearchSection(onSearch = { name ->
            viewModel.onEvent(SearchEvent.Search(name))
        })
    }
}

@Composable
fun SearchSection(
    onSearch: (String) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            trailingIcon = {
                IconButton(onClick = {
                    onSearch(name)
                }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_baseline_search_24
                        ),
                        contentDescription = "SearchIcon"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}