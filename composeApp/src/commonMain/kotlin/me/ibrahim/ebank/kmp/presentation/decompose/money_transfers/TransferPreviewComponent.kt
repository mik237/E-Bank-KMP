package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferPreviewUiAction

interface TransferPreviewComponent {
    fun onAction(action: TransferPreviewUiAction)
}