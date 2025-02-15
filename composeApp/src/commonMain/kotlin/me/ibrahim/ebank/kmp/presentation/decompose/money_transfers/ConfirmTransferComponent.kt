package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferConfirmationUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

interface ConfirmTransferComponent {
    val state : Value<TransferPreviewState>
    fun onAction(action: TransferConfirmationUiAction)
}