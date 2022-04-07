package com.beomsu317.cocktailrecipeapp.presentation.search

sealed class SearchEvent {
    data class Search(val name: String): SearchEvent()
}
