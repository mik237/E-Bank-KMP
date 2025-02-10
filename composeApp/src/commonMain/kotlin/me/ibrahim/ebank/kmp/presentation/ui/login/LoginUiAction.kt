package me.ibrahim.ebank.kmp.presentation.ui.login

interface LoginUiAction {
    data class TypeUsername(val username: String) : LoginUiAction
    data class TypePassword(val password: String) : LoginUiAction
}