package me.ibrahim.ebank.kmp.presentation.decompose.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiAction
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiState

class LoginComponentImpl(componentContext: ComponentContext) : LoginComponent,
    ComponentContext by componentContext {

    private val _state = MutableValue<LoginUiState>(LoginUiState.Default)
    override val state: Value<LoginUiState> = _state

    private val _username = MutableValue("")
    override val username: Value<String> = _username

    private val _password = MutableValue("")
    override val password: Value<String> = _password

    override fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.TypeUsername -> _username.value = action.username
            is LoginUiAction.TypePassword -> _password.value = action.password
        }
    }
}