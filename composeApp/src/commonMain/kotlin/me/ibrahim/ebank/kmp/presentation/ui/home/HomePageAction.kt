package me.ibrahim.ebank.kmp.presentation.ui.home

sealed interface HomePageAction {
    data class OnCardClick(val cardId: Int) : HomePageAction
}