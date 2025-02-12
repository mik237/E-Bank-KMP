package me.ibrahim.ebank.kmp.presentation.decompose.card_settings

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUIAction

class CardSettingsComponentImpl(card: Card, val onBackClick: () -> Unit) : CardSettingsComponent, InstanceKeeper.Instance {

    private var _card = MutableStateFlow(card)
    override val card: StateFlow<Card> = _card
    override fun onAction(action: CardSettingsUIAction) {
        when (action) {
            CardSettingsUIAction.OnBackClick -> onBackClick()
        }
    }
}