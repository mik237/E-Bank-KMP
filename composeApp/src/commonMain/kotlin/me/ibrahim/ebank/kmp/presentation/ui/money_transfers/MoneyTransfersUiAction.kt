package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

sealed interface MoneyTransfersUiAction {
    data object OnBackClick : MoneyTransfersUiAction
    data class OnSearch(val searchKey: String) : MoneyTransfersUiAction
}