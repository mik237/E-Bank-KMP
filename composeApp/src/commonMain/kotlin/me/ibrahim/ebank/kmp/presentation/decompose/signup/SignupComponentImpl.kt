package me.ibrahim.ebank.kmp.presentation.decompose.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupState
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupUiAction

class SignupComponentImpl(
    componentContext: ComponentContext,
    private val onLoginClicked: () -> Unit
) : SignupComponent, ComponentContext by componentContext {

    private val _state = MutableValue(SignupState())
    override val state: Value<SignupState> = _state

    override fun onAction(action: SignupUiAction) {
        when (action) {
            is SignupUiAction.OnCheckBoxAction -> {
                _state.update { it.copy(termsAndConditionAccepted = action.checked) }
            }

            is SignupUiAction.TypeCNIC -> {
                _state.update { it.copy(cnic = action.cnic) }
            }

            is SignupUiAction.TypeEmail -> {
                _state.update { it.copy(email = action.email) }
            }

            is SignupUiAction.TypeMobileNumber -> {
                _state.update { it.copy(mobileNumber = action.mobile) }
            }

            is SignupUiAction.TypePassword -> {
                _state.update { it.copy(password = action.password) }
            }

            is SignupUiAction.TypeUsername -> {
                _state.update { it.copy(username = action.username) }
            }

            SignupUiAction.OnLoginClick -> onLoginClicked()

            SignupUiAction.OnSignupClick -> {}
        }
    }

}