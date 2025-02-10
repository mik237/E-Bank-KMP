package me.ibrahim.ebank.kmp.presentation.ui.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val uiState: LoginUiState = LoginUiState.Default
)

sealed interface LoginUiState {
    data object Default : LoginUiState
    data object Logging : LoginUiState
    data object Success : LoginUiState
    data class Error(val error: String) : LoginUiState
}
