package me.ibrahim.ebank.kmp.presentation.ui.login

interface LoginUiState {
    data object Default : LoginUiState
    data object Logging : LoginUiState
    data object Success : LoginUiState
    data class Error(val error: String) : LoginUiState
}