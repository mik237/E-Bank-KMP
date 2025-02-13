package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

data class MoneyTransferState(
    val amount: Double = 0.0,
    val searchKey: String = "",
    val recentTransfers: List<RecentTransfer> = emptyList()
)
