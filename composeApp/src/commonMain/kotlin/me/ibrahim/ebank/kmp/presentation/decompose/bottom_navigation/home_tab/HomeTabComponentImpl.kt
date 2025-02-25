@file:OptIn(DelicateDecomposeApi::class)

package me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab

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
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponentImpl
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageAction

class HomeTabComponentImpl(componentContext: ComponentContext) : HomeTabComponent, ComponentContext by componentContext {

    private val homeTabNavigation = StackNavigation<HomeTabNavigationConfig>()

    override val homeStack: Value<ChildStack<*, HomeTabComponent.HomeTabDestinationChild>> = childStack(
        source = homeTabNavigation,
        serializer = HomeTabNavigationConfig.serializer(),
        initialConfiguration = HomeTabNavigationConfig.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: HomeTabNavigationConfig, context: ComponentContext): HomeTabComponent.HomeTabDestinationChild {
        return when (config) {
            HomeTabNavigationConfig.BankTransfer -> HomeTabComponent.HomeTabDestinationChild.BankTransfer(component = buildBankTransferComponent())
            is HomeTabNavigationConfig.CardSettings -> HomeTabComponent.HomeTabDestinationChild.CardSettings(
                component = buildCardSettingsComponent(
                    config.card
                )
            )

            HomeTabNavigationConfig.Home -> HomeTabComponent.HomeTabDestinationChild.Home(component = buildHomeComponent(context))
            is HomeTabNavigationConfig.MoneyTransfer -> HomeTabComponent.HomeTabDestinationChild.MoneyTransfer(
                component = buildMoneyTransferComponent(
                    config.card
                )
            )

            HomeTabNavigationConfig.PayBills -> HomeTabComponent.HomeTabDestinationChild.PayBills(component = buildPayBillsComponent())
            is HomeTabNavigationConfig.TransferConfirmation -> HomeTabComponent.HomeTabDestinationChild.TransferConfirmation(
                component = buildTransferConfirmationComponent(
                    config.card,
                    config.amount,
                    config.recipientInfo
                )
            )

            is HomeTabNavigationConfig.TransferPreview -> HomeTabComponent.HomeTabDestinationChild.TransferPreview(
                component = buildTransferPreviewComponent(
                    config.card,
                    config.amount,
                    config.recipientInfo
                )
            )

            is HomeTabNavigationConfig.TransferSuccess -> HomeTabComponent.HomeTabDestinationChild.TransferSuccess(
                component = buildTransferSuccessComponent(
                    config.card,
                    config.amount,
                    config.recipientInfo
                )
            )
        }
    }

    private fun buildHomeComponent(context: ComponentContext): HomeComponent {
        return HomeComponentImpl(doAction = { action ->
            when (action) {
                is HomePageAction.OnCardClick -> homeTabNavigation.push(HomeTabNavigationConfig.CardSettings(action.card))
                is HomePageAction.OnQuickActionClick -> {
                    when (action.type) {
                        QuickAction.MoneyTransfer -> homeTabNavigation.push(HomeTabNavigationConfig.MoneyTransfer(action.card))
                        QuickAction.PayBill -> homeTabNavigation.push(HomeTabNavigationConfig.PayBills)
                        QuickAction.BankToBank -> homeTabNavigation.push(HomeTabNavigationConfig.BankTransfer)
                    }
                }
            }
        })
    }

    private fun buildBankTransferComponent(): BankTransferComponent {
        return BankTransferComponentImpl(onBack = { homeTabNavigation.pop() })
    }

    private fun buildCardSettingsComponent(card: Card): CardSettingsComponent {
        return CardSettingsComponentImpl(card = card, onBackClick = { homeTabNavigation.pop() })
    }

    private fun buildMoneyTransferComponent(card: Card): MoneyTransferComponent {
        return MoneyTransferComponentImpl(card = card, onBackClick = { homeTabNavigation.pop() },
            onContinue = { selectedCard, recentTransfer, amount ->
                homeTabNavigation.replaceCurrent(HomeTabNavigationConfig.TransferPreview(selectedCard, recentTransfer, amount))
            })
    }

    private fun buildTransferPreviewComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): TransferPreviewComponent {
        return TransferPreviewComponentImpl(
            card = card, amount = amount, recipientInfo = recipientInfo,
            onBackClick = { homeTabNavigation.pop() },
            onContinueClick = { homeTabNavigation.replaceCurrent(HomeTabNavigationConfig.TransferConfirmation(card, recipientInfo, amount)) })
    }

    private fun buildTransferConfirmationComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): ConfirmTransferComponent {
        return ConfirmTransferComponentImpl(card = card, amount = amount, recipientInfo = recipientInfo,
            onBackClick = { homeTabNavigation.pop() }, onContinueClick = {
                homeTabNavigation.replaceCurrent(HomeTabNavigationConfig.TransferSuccess(card, recipientInfo, amount))
            })
    }

    private fun buildTransferSuccessComponent(card: Card, amount: Double, recipientInfo: RecipientInfo): TransferSuccessComponent {
        return TransferSuccessComponentImpl(card = card, amount = amount, recipientInfo = recipientInfo,
            onBack = { homeTabNavigation.pop() })
    }



    private fun buildPayBillsComponent(): PayBillsComponent {
        return PayBillsComponentImpl(onBack = { homeTabNavigation.pop() }, onNext = {})
    }


    @Serializable
    sealed class HomeTabNavigationConfig {
        data object Home : HomeTabNavigationConfig()
        data class CardSettings(val card: Card) : HomeTabNavigationConfig()
        data class MoneyTransfer(val card: Card) : HomeTabNavigationConfig()
        data class TransferPreview(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : HomeTabNavigationConfig()
        data class TransferConfirmation(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : HomeTabNavigationConfig()
        data class TransferSuccess(val card: Card, val recipientInfo: RecipientInfo, val amount: Double) : HomeTabNavigationConfig()
        data object PayBills : HomeTabNavigationConfig()
        data object BankTransfer : HomeTabNavigationConfig()
    }
}