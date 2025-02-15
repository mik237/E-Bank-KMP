package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states

import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo

data class TransferPreviewState(
    val card: Card,
    val recipientInfo: RecipientInfo,
    val amount: Double
)
