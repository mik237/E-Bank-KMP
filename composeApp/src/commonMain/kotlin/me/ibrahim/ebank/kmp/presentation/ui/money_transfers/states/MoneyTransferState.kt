package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states

import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

data class MoneyTransferState(
    val amount: Double = 0.0,
    val searchKey: String = "",
    val currentCard: Card? = null,
    val recentTransfer: RecentTransfer? = null,
    val recentTransfers: List<RecentTransfer> = emptyList(),
    val uiState: MoneyTransferUiState = MoneyTransferUiState.Default
)

sealed interface MoneyTransferUiState {
    data object Default : MoneyTransferUiState
    data object Continue : MoneyTransferUiState
    data class Error(val error: String = "Choose Recipient") : MoneyTransferUiState
}
