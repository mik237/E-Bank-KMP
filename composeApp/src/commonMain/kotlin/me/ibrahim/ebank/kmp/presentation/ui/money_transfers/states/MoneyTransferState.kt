package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states

import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo

data class MoneyTransferState(
    val amount: Double = 0.0,
    val searchKey: String = "",
    val currentCard: Card? = null,
    val recipientInfo: RecipientInfo? = null,
    val recipientInfos: List<RecipientInfo> = emptyList(),
    val uiState: MoneyTransferUiState = MoneyTransferUiState.Default
)

sealed interface MoneyTransferUiState {
    data object Default : MoneyTransferUiState
    data object Continue : MoneyTransferUiState
    data class Error(val error: String = "Choose Recipient") : MoneyTransferUiState
}
