package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferPreviewUiAction

class TransferPreviewComponentImpl(val onBackClick: () -> Unit) : TransferPreviewComponent {

    override fun onAction(action: TransferPreviewUiAction) {
        when (action) {
            TransferPreviewUiAction.OnBackClick -> onBackClick()
            TransferPreviewUiAction.OnContinueClick -> {}
        }
    }
}