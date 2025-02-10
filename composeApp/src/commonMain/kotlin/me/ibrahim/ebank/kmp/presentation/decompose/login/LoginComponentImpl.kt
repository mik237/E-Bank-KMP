package me.ibrahim.ebank.kmp.presentation.decompose.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiState

class LoginComponentImpl(componentContext: ComponentContext) : LoginComponent,
    ComponentContext by componentContext {
    private val _state = MutableValue<LoginUiState>(LoginUiState.Default)
    override val state: Value<LoginUiState> = _state
}