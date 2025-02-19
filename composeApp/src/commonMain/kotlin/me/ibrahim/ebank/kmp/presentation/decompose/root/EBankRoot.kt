package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponent
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponent
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponent

interface EBankRoot {

    val backStack: Value<ChildStack<*, MainDestinationChild>>

    sealed class MainDestinationChild {
        data class Splash(val component: SplashComponent) : MainDestinationChild()
        data class OnBoarding(val component: OnBoardingComponent) : MainDestinationChild()
        data class Login(val component: LoginComponent) : MainDestinationChild()
        data class Signup(val component: SignupComponent) : MainDestinationChild()
        data class Home(val component: HomeComponent) : MainDestinationChild()
        data class CardSettings(val component: CardSettingsComponent) : MainDestinationChild()
        data class MoneyTransfer(val component: MoneyTransferComponent) : MainDestinationChild()
        data class TransferPreview(val component: TransferPreviewComponent) : MainDestinationChild()
        data class TransferConfirmation(val component: ConfirmTransferComponent) : MainDestinationChild()
        data class TransferSuccess(val component: TransferSuccessComponent) : MainDestinationChild()
        data class PayBills(val component: PayBillsComponent) : MainDestinationChild()
    }
}