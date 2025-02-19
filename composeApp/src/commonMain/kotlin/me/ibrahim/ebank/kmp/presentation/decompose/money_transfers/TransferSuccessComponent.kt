package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferSuccessUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

interface TransferSuccessComponent {
    val state : Value<TransferPreviewState>
    fun onAction(action: TransferSuccessUiAction)
}