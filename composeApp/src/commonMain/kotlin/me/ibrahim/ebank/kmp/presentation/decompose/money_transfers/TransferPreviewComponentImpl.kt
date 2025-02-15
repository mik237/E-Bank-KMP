package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferPreviewUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

class TransferPreviewComponentImpl(
    card: Card, amount: Double, recipientInfo: RecipientInfo,
    val onBackClick: () -> Unit,
    val onContinueClick: () -> Unit
) :
    TransferPreviewComponent {

    private val _state = MutableValue(TransferPreviewState(card = card, recipientInfo = recipientInfo, amount = amount))
    override val state: Value<TransferPreviewState>
        get() = _state

    override fun onAction(action: TransferPreviewUiAction) {
        when (action) {
            TransferPreviewUiAction.OnBackClick -> onBackClick()
            TransferPreviewUiAction.OnContinueClick -> onContinueClick()
        }
    }
}