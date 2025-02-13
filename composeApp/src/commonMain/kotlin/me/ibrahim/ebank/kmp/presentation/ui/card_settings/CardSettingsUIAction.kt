package me.ibrahim.ebank.kmp.presentation.ui.card_settings

sealed interface CardSettingsUIAction {
    data object OnBackClick : CardSettingsUIAction
    data class LockCard(val lock: Boolean) : CardSettingsUIAction
    data class DeActivateCard(val deactivate: Boolean) : CardSettingsUIAction
}