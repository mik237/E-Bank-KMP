package me.ibrahim.ebank.kmp.presentation.ui.card_settings

import me.ibrahim.ebank.kmp.domain.models.Card

data class CardSettingsState(
    val lockCard: Boolean = false,
    val deactivateCard: Boolean = false,
    val selectedCard: Card
)
