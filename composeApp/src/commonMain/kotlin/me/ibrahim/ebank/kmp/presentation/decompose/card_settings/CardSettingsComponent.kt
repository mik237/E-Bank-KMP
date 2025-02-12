package me.ibrahim.ebank.kmp.presentation.decompose.card_settings

import kotlinx.coroutines.flow.StateFlow
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUIAction

interface CardSettingsComponent {
    val card: StateFlow<Card>
    fun onAction(action: CardSettingsUIAction)
}