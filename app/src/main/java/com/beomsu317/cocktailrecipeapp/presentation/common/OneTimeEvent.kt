package com.beomsu317.cocktailrecipeapp.presentation.common

sealed class OneTimeEvent {
    data class Error(val message: String): OneTimeEvent()
}
