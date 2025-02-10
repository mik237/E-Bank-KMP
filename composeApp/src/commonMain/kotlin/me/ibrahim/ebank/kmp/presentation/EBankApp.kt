package me.ibrahim.ebank.kmp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginPage
import me.ibrahim.ebank.kmp.presentation.ui.onboarding.OnBoardingPage
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupPage
import me.ibrahim.ebank.kmp.presentation.ui.splash.SplashPage

@Composable
fun EBankApp(root: EBankRoot) {
    MaterialTheme {

        Children(
            stack = root.backStack,
            animation = stackAnimation(slide())
        ) {
            when (val child = it.instance) {
                is EBankRoot.MainDestinationChild.Splash -> SplashPage(child.component)
                is EBankRoot.MainDestinationChild.OnBoarding -> OnBoardingPage(child.component)
                is EBankRoot.MainDestinationChild.Login -> LoginPage(child.component)
                is EBankRoot.MainDestinationChild.Signup -> SignupPage(child.component)
            }
        }
    }
}