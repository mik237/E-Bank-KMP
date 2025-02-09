package me.ibrahim.ebank.kmp.presentation.ui.splash

sealed interface SplashUiState {
    data object Waiting : SplashUiState
    data object Finished : SplashUiState
}