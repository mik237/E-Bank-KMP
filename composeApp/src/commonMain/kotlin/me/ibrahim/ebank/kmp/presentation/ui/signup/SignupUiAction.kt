package me.ibrahim.ebank.kmp.presentation.ui.signup

sealed interface SignupUiAction {
    data class TypeUsername(val username: String) : SignupUiAction
    data class TypePassword(val password: String) : SignupUiAction
    data class TypeEmail(val email: String) : SignupUiAction
    data class TypeCNIC(val cnic: String) : SignupUiAction
    data class TypeMobileNumber(val mobile: String) : SignupUiAction
    data object OnSignupClick : SignupUiAction
    data object OnLoginClick : SignupUiAction
    data class OnCheckBoxAction(val checked: Boolean) : SignupUiAction
}