package me.ibrahim.ebank.kmp.presentation.decompose.card_settings

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsState
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUIAction

interface CardSettingsComponent {
    val state : Value<CardSettingsState>
    fun onAction(action: CardSettingsUIAction)
}