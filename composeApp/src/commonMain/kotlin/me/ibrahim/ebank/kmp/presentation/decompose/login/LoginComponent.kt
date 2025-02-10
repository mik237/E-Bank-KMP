package me.ibrahim.ebank.kmp.presentation.decompose.login

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginState
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiAction

interface LoginComponent {
    val state: Value<LoginState>

    fun onAction(action: LoginUiAction)
}