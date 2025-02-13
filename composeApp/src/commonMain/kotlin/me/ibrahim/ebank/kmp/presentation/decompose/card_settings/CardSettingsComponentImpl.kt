package me.ibrahim.ebank.kmp.presentation.decompose.card_settings

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsState
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUIAction

class CardSettingsComponentImpl(card: Card, val onBackClick: () -> Unit) : CardSettingsComponent, InstanceKeeper.Instance {

    private val _state = MutableValue(CardSettingsState(selectedCard = card))
    override val state: Value<CardSettingsState>
        get() = _state

    override fun onAction(action: CardSettingsUIAction) {
        when (action) {
            CardSettingsUIAction.OnBackClick -> onBackClick()
            is CardSettingsUIAction.DeActivateCard -> _state.update { it.copy(deactivateCard = action.deactivate) }
            is CardSettingsUIAction.LockCard -> _state.update { it.copy(lockCard = action.lock) }
        }
    }
}