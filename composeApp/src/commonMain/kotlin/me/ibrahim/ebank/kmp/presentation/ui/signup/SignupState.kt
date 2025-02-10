package me.ibrahim.ebank.kmp.presentation.ui.signup

data class SignupState(
    val uiState: SignupUiState = SignupUiState.Default,
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val cnic: String = "",
    val mobileNumber: String = "",
    val termsAndConditionAccepted: Boolean = false
)

sealed interface SignupUiState {
    data object Default : SignupUiState
    data object SigningUp : SignupUiState
    data object Success : SignupUiState
    data class Error(val error: String) : SignupUiState
}