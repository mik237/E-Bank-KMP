package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions

sealed interface TransferSuccessUiAction {
    data object OnBackClick : TransferSuccessUiAction
    data object OnViewReceipt : TransferSuccessUiAction
}