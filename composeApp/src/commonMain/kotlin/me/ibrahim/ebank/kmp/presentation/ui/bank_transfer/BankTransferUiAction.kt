package me.ibrahim.ebank.kmp.presentation.ui.bank_transfer

sealed interface BankTransferUiAction {
    data object OnBackClick : BankTransferUiAction
}