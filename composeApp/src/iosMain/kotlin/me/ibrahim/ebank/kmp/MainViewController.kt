package me.ibrahim.ebank.kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import me.ibrahim.ebank.kmp.presentation.EBankApp
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.BottomNavComponentImpl

fun MainViewController() = ComposeUIViewController {
    val root = BottomNavComponentImpl(DefaultComponentContext(lifecycle = LifecycleRegistry()))
    EBankApp(root = root)
}