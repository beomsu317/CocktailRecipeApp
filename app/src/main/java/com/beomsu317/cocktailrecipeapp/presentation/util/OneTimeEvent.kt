package com.beomsu317.cocktailrecipeapp.presentation.util

sealed class OneTimeEvent {
    data class ShowSnackbar(val message: String): OneTimeEvent()
}
