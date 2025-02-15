package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions

sealed interface TransferConfirmationUiAction {
    data object OnBackClick : TransferConfirmationUiAction
    data object OnContinueClick : TransferConfirmationUiAction
}