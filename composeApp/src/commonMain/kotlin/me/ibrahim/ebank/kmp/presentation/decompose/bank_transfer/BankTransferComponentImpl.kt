package me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer

import me.ibrahim.ebank.kmp.presentation.ui.bank_transfer.BankTransferUiAction

class BankTransferComponentImpl(private val onBack: () -> Unit) : BankTransferComponent {

    override fun onAction(action: BankTransferUiAction) {
        when (action) {
            BankTransferUiAction.OnBackClick -> onBack()
        }
    }

}