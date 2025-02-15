package me.ibrahim.ebank.kmp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUI
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePage
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginPage
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferConfirmationUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferPreviewUI
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
                is EBankRoot.MainDestinationChild.Home -> HomePage(child.component)
                is EBankRoot.MainDestinationChild.CardSettings -> CardSettingsUI(child.component)
                is EBankRoot.MainDestinationChild.MoneyTransfer -> MoneyTransfersUI(child.component)
                is EBankRoot.MainDestinationChild.TransferPreview -> TransferPreviewUI(child.component)
                is EBankRoot.MainDestinationChild.TransferConfirmation -> TransferConfirmationUI(child.component)
            }
        }
    }
}