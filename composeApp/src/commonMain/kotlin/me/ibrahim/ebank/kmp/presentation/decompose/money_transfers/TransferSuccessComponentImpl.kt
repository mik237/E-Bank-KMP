package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferSuccessUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

class TransferSuccessComponentImpl(card: Card, amount: Double, recipientInfo: RecipientInfo, val onBack: () -> Unit) : TransferSuccessComponent {

    private val _state = MutableValue(TransferPreviewState(card, recipientInfo, amount))
    override val state: Value<TransferPreviewState>
        get() = _state

    override fun onAction(action: TransferSuccessUiAction) {
        when (action) {
            TransferSuccessUiAction.OnBackClick -> onBack()
            TransferSuccessUiAction.OnViewReceipt -> {}
        }
    }
}