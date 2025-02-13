package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransferState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUiAction

interface MoneyTransferComponent {
    val state: Value<MoneyTransferState>
    fun onAction(action: MoneyTransfersUiAction)
}