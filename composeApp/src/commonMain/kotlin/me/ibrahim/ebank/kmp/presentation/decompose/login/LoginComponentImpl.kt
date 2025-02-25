package me.ibrahim.ebank.kmp.presentation.decompose.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginState
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiAction

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val onSignupClicked: () -> Unit,
    private val onLoginClicked: (username: String, password: String) -> Unit
) : LoginComponent,
    ComponentContext by componentContext {

    private val _state = MutableValue(LoginState())
    override val state: Value<LoginState> = _state

    override fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.TypeUsername -> {
                _state.update { it.copy(username = action.username) }
            }

            is LoginUiAction.TypePassword -> {
                _state.update { it.copy(password = action.password) }
            }

            LoginUiAction.OnSignupClicked -> onSignupClicked()
            LoginUiAction.OnLoginClicked -> onLoginClicked(state.value.username, state.value.password)
        }
    }
}