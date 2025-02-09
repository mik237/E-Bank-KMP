package me.ibrahim.ebank.kmp.presentation.decompose.splash

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.splash.SplashUiState

interface SplashComponent {
    val state: Value<SplashUiState>
    fun onSplashFinished()
}