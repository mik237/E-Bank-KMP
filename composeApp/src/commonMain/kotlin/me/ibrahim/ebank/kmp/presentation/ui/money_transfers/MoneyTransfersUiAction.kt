package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

sealed interface MoneyTransfersUiAction {
    data object OnBackClick : MoneyTransfersUiAction
    data class OnSearch(val searchKey: String) : MoneyTransfersUiAction
    data class OnRecentTransferClick(val recentTransfer: RecentTransfer) : MoneyTransfersUiAction
    data class OnAmountSelected(val amount: Double) : MoneyTransfersUiAction
    data class OnCardSelected(val card: Card) : MoneyTransfersUiAction
    data class OnContinue(val card: Card, val recentTransfer: RecentTransfer, val amount: Double) : MoneyTransfersUiAction
}