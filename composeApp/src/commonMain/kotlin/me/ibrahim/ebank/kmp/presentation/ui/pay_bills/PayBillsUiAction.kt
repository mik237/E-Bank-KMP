package me.ibrahim.ebank.kmp.presentation.ui.pay_bills

sealed interface PayBillsUiAction {
    data object OnBackClick : PayBillsUiAction
    data object OnNextClick : PayBillsUiAction
}