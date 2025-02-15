package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferPreviewUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

interface TransferPreviewComponent {
    val state: Value<TransferPreviewState>
    fun onAction(action: TransferPreviewUiAction)
}