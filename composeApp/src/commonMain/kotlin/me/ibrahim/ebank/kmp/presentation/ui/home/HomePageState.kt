package me.ibrahim.ebank.kmp.presentation.ui.home

import me.ibrahim.ebank.kmp.domain.Card

data class HomePageState(
    val cards: List<Card> = emptyList(),
    val uiState: HomePageUiState = HomePageUiState.Default
)


sealed interface HomePageUiState {
    data object Default : HomePageUiState
    data object Loading : HomePageUiState
    data object Success : HomePageUiState
    data class Error(val error: String) : HomePageUiState
}
