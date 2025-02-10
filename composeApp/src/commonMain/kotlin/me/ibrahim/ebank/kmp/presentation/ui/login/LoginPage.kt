package me.ibrahim.ebank.kmp.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent

@Composable
fun LoginPage(component: LoginComponent) {

    val state by component.state.subscribeAsState()

}