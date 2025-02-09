package me.ibrahim.ebank.kmp.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot
import me.ibrahim.ebank.kmp.presentation.ui.splash.SplashPage

@Composable
fun EBankApp(root: EBankRoot) {
    MaterialTheme {
        Children(stack = root.backStack) {
            when (val child = it.instance) {
                is EBankRoot.MainDestinationChild.Splash -> SplashPage(child.component)
                EBankRoot.MainDestinationChild.OnBoarding -> {}
            }
        }
    }
}