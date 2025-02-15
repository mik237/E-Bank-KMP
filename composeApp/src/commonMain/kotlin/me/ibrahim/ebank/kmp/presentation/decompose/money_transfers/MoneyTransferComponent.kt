package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.MoneyTransferState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.MoneyTransfersUiAction

interface MoneyTransferComponent {
    val state: Value<MoneyTransferState>
    fun onAction(action: MoneyTransfersUiAction)
}