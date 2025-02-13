package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

sealed interface MoneyTransfersUiAction {
    data object OnBackClick: MoneyTransfersUiAction
}