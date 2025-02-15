package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

sealed interface TransferPreviewUiAction {
    data object OnBackClick : TransferPreviewUiAction
    data object OnContinueClick: TransferPreviewUiAction
}