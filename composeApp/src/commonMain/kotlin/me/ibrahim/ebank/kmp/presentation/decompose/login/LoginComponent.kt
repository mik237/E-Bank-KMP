package me.ibrahim.ebank.kmp.presentation.decompose.login

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginUiState

interface LoginComponent {
    val state: Value<LoginUiState>
}