package me.ibrahim.ebank.kmp.presentation.ui.card_settings

sealed interface CardSettingsUIAction {
    data object OnBackClick : CardSettingsUIAction
}