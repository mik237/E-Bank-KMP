package me.ibrahim.ebank.kmp.presentation.ui.home

import me.ibrahim.ebank.kmp.domain.models.Card

sealed interface HomePageAction {
    data class OnCardClick(val card: Card) : HomePageAction
}