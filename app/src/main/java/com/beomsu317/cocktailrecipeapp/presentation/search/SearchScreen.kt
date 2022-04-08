package com.beomsu317.cocktailrecipeapp.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beomsu317.cocktailrecipeapp.R
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.presentation.common.OneTimeEvent
import com.beomsu317.cocktailrecipeapp.presentation.common.components.CocktailInfos
import com.beomsu317.cocktailrecipeapp.presentation.search.components.SearchTopBar
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    onIngredientClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val oneTimeEventFlow = viewModel.oneTimeEventFlow
    LaunchedEffect(key1 = oneTimeEventFlow) {
        oneTimeEventFlow.collectLatest { oneTimeEvent ->
            when (oneTimeEvent) {
                is OneTimeEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        oneTimeEvent.message,
                        null,
                        SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTopBar()
        SearchSection(
            name = state.name,
            onSearch = { name ->
                viewModel.onEvent(SearchEvent.Search(name))
            })
        CocktailInfoSection(
            cocktailInfos = state.cocktails,
            isLoading = state.isLoading,
            onIngredientClick = onIngredientClick,
            ids = state.ids,
            onLikeClick = { cocktailInfo ->
                viewModel.onEvent(SearchEvent.ToggleCocktailInfo(cocktailInfo))
            }
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchSection(
    name: String,
    onSearch: (String) -> Unit
) {
    var name by remember {
        mutableStateOf(name)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
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
                    keyboardController?.hide()
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
                .padding(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CocktailInfoSection(
    cocktailInfos: List<CocktailInfo>,
    isLoading: Boolean,
    onIngredientClick: (String) -> Unit,
    ids: List<Int>,
    onLikeClick: (CocktailInfo) -> Unit
) {
    CocktailInfos(
        cocktailInfos = cocktailInfos,
        isLoading = isLoading,
        onIngredientClick = onIngredientClick,
        ids = ids,
        useIndicator = false,
        onLikeClick = { cocktailInfo ->
            onLikeClick(cocktailInfo)
        },
        modifier = Modifier
            .fillMaxSize()
    )
}
