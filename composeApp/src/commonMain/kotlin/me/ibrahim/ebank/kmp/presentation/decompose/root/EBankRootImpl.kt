@file:OptIn(DelicateDecomposeApi::class)

package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import me.ibrahim.ebank.kmp.domain.constants.QuickAction
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo
import me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer.BankTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer.BankTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponent
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponent
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponentImpl
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageAction

class EBankRootImpl(
    componentContext: ComponentContext
) : EBankRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainNavigationConfig>()

    private val _backStack = childStack(
        source = navigation,
        serializer = MainNavigationConfig.serializer(),
        handleBackButton = true,
        initialConfiguration = MainNavigationConfig.Home,
        childFactory = ::createChild
    )

    override val backStack: Value<ChildStack<MainNavigationConfig, EBankRoot.MainDestinationChild>> = _backStack

    private fun createChild(config: MainNavigationConfig, context: ComponentContext): EBankRoot.MainDestinationChild {
        return when (config) {
            MainNavigationConfig.Splash -> EBankRoot.MainDestinationChild.Splash(component = buildSplashComponent())
            MainNavigationConfig.OnBoarding -> EBankRoot.MainDestinationChild.OnBoarding(component = buildOnBoardingComponent())
            MainNavigationConfig.Login -> EBankRoot.MainDestinationChild.Login(component = buildLoginComponent(context))
            MainNavigationConfig.Signup -> EBankRoot.MainDestinationChild.Signup(component = buildSignupComponent(context))
            MainNavigationConfig.Home -> EBankRoot.MainDestinationChild.Home(component = buildHomeComponent())
            is MainNavigationConfig.CardSettings -> EBankRoot.MainDestinationChild.CardSettings(
                component = buildCardSettingsComponent(
                    config.card
                )
            )

            is MainNavigationConfig.MoneyTransfer -> EBankRoot.MainDestinationChild.MoneyTransfer(
                component = buildMoneyTransferComponent(
                    config.card
                )
            )

            is MainNavigationConfig.TransferPreview -> EBankRoot.MainDestinationChild.TransferPreview(
                component = buildTransferPreviewComponent(config.card, config.amount, config.recipientInfo)
            )

            is MainNavigationConfig.TransferConfirmation -> EBankRoot.MainDestinationChild.TransferConfirmation(
                component = buildTransferConfirmationComponent(
                    config.card,
                    config.amount,
                    config.recipientInfo
                )
            )

            is MainNavigationConfig.TransferSuccess -> EBankRoot.MainDestinationChild.TransferSuccess(
                component = buildTransferSuccessComponent(
                    config.card,
                    config.amount,
                    config.recipientInfo
                )
            )

            MainNavigationConfig.PayBills -> EBankRoot.MainDestinationChild.PayBills(component = buildPayBillsComponent())
            MainNavigationConfig.BankTransfer -> EBankRoot.MainDestinationChild.BankTransfer(component = buildBankTransferComponent())
        }
    }


    private fun buildSplashComponent(): SplashComponent {
        return SplashComponentImpl(splashFinished = { onBoarded ->
            if (onBoarded.not()) navigation.replaceCurrent(MainNavigationConfig.OnBoarding)
            else navigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildOnBoardingComponent(): OnBoardingComponent {
        return OnBoardingComponentImpl(skipBoarding = {
            navigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildLoginComponent(context: ComponentContext): LoginComponent {
        return LoginComponentImpl(
            componentContext = context,
            onSignupClicked = {
                navigation.replaceCurrent(MainNavigationConfig.Signup)
            }
        )
    }

    private fun buildSignupComponent(context: ComponentContext): SignupComponent {
        return SignupComponentImpl(
            componentContext = context,
            onLoginClicked = {
                navigation.replaceCurrent(MainNavigationConfig.Login)
            })
    }

    private fun buildHomeComponent(): HomeComponent {
        return HomeComponentImpl(doAction = { action ->
            when (action) {
                is HomePageAction.OnCardClick -> navigation.push(MainNavigationConfig.CardSettings(action.card))
                is HomePageAction.OnQuickActionClick -> {
                    when (action.type) {
                        QuickAction.MoneyTransfer -> navigation.push(MainNavigationConfig.MoneyTransfer(action.card))
                        QuickAction.PayBill -> navigation.push(MainNavigationConfig.PayBills)
                        QuickAction.BankToBank -> navigation.push(MainNavigationConfig.BankTransfer)
                    }
                }
            }
        })
    }

    private fun buildCardSettingsComponent(card: Card): CardSettingsComponent {
        return CardSettingsComponentImpl(card = card, onBackClick = { navigation.pop() })
    }

    private fun buildMoneyTransferComponent(card: Card): MoneyTransferComponent {
        return MoneyTransferComponentImpl(card = card, onBackClick = { navigation.pop() },
            onContinue = { selectedCard, recentTransfer, amount ->
                navigation.replaceCurrent(MainNavigationConfig.TransferPreview(selectedCard, recentTransfer, amount))
            })
    }

    private fun buildTransferPreviewComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): TransferPreviewComponent {
        return TransferPreviewComponentImpl(
            card = card, amount = amount, recipientInfo = recipientInfo,
            onBackClick = { navigation.pop() },
            onContinueClick = { navigation.replaceCurrent(MainNavigationConfig.TransferConfirmation(card, recipientInfo, amount)) })
    }

    private fun buildTransferConfirmationComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): ConfirmTransferComponent {
        return ConfirmTransferComponentImpl(card = card, amount = amount, recipientInfo = recipientInfo,
            onBackClick = { navigation.pop() }, onContinueClick = {
                navigation.replaceCurrent(MainNavigationConfig.TransferSuccess(card, recipientInfo, amount))
            })
    }

    private fun buildTransferSuccessComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): TransferSuccessComponent {
        return TransferSuccessComponentImpl(card = card, amount = amount, recipientInfo = recipientInfo,
            onBack = { navigation.pop() })
    }

    private fun buildBankTransferComponent(): BankTransferComponent {
        return BankTransferComponentImpl(onBack = { navigation.pop() })
    }

    private fun buildPayBillsComponent(): PayBillsComponent {
        return PayBillsComponentImpl(onBack = { navigation.pop() }, onNext = {})
    }

    @Serializable
    sealed class MainNavigationConfig {
        @Serializable
        data object Splash : MainNavigationConfig()
        data object OnBoarding : MainNavigationConfig()
        data object Login : MainNavigationConfig()
        data object Signup : MainNavigationConfig()
        data object Home : MainNavigationConfig()
        data class CardSettings(val card: Card) : MainNavigationConfig()
        data class MoneyTransfer(val card: Card) : MainNavigationConfig()
        data class TransferPreview(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : MainNavigationConfig()
        data class TransferConfirmation(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : MainNavigationConfig()
        data class TransferSuccess(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : MainNavigationConfig()
        data object PayBills : MainNavigationConfig()
        data object BankTransfer : MainNavigationConfig()
    }
}