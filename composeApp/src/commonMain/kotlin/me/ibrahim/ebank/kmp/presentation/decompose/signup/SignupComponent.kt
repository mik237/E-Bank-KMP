package me.ibrahim.ebank.kmp.presentation.decompose.signup

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupState
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupUiAction

interface SignupComponent {
    val state: Value<SignupState>

    fun onAction(action: SignupUiAction)
}