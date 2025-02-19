package me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer

import me.ibrahim.ebank.kmp.presentation.ui.bank_transfer.BankTransferUiAction

interface BankTransferComponent {
    fun onAction(action: BankTransferUiAction)
}