package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferConfirmationUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

class ConfirmTransferComponentImpl(card: Card, amount: Double, recipientInfo: RecipientInfo, val onBackClick: () -> Unit) : ConfirmTransferComponent {

    private val _state = MutableValue(TransferPreviewState(card, recipientInfo, amount))
    override val state: Value<TransferPreviewState>
        get() = _state

    override fun onAction(action: TransferConfirmationUiAction) {

        when (action) {
            TransferConfirmationUiAction.OnBackClick -> onBackClick()
            TransferConfirmationUiAction.OnContinueClick -> {}
        }
    }
}