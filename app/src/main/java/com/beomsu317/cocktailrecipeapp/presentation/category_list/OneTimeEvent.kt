package com.beomsu317.cocktailrecipeapp.presentation.category_list

sealed class CategoryListOneTimeEvent {
    data class ErrorEvent(val error: String): CategoryListOneTimeEvent()
}
