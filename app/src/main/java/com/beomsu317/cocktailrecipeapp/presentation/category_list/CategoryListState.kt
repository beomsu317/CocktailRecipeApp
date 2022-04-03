package com.beomsu317.cocktailrecipeapp.presentation.category_list

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList()
)

