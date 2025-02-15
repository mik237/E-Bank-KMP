package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferPreviewUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState

class TransferPreviewComponentImpl(card: Card, amount: Double, recentTransfer: RecentTransfer, val onBackClick: () -> Unit) :
    TransferPreviewComponent {

    private val _state = MutableValue(TransferPreviewState(card = card, recentTransfer = recentTransfer, amount = amount))
    override val state: Value<TransferPreviewState>
        get() = _state

    override fun onAction(action: TransferPreviewUiAction) {
        when (action) {
            TransferPreviewUiAction.OnBackClick -> onBackClick()
            TransferPreviewUiAction.OnContinueClick -> {}
        }
    }
}