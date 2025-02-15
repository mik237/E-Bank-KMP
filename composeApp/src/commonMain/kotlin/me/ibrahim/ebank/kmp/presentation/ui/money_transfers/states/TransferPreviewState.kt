package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states

import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

data class TransferPreviewState(
    val card: Card,
    val recentTransfer: RecentTransfer,
    val amount: Double
)
