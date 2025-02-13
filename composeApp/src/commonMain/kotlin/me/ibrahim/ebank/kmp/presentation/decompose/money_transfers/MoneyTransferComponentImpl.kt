package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransferState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUiAction

class MoneyTransferComponentImpl(val onBackClick: () -> Unit) : MoneyTransferComponent {

    private val _state = MutableValue(MoneyTransferState(amount = 0.0))

    override val state: Value<MoneyTransferState>
        get() = _state

    override fun onAction(action: MoneyTransfersUiAction) {
        when (action) {
            MoneyTransfersUiAction.OnBackClick -> onBackClick()
        }
    }
}