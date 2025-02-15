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
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponentImpl
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
        initialConfiguration = MainNavigationConfig.Splash,
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
                component = buildTransferPreviewComponent(
                    config.card
                )
            )
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
                        QuickAction.PayBill -> {}
                        QuickAction.BankToBank -> {}
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

    private fun buildTransferPreviewComponent(card: Card): TransferPreviewComponent {
        return TransferPreviewComponentImpl(onBackClick = { navigation.pop() })
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
        data class TransferPreview(val card: Card, val recentTransfer: RecentTransfer, val amount: Double) : MainNavigationConfig()
    }
}