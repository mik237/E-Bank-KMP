package me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer.BankTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponent
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponent
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponent

interface HomeTabComponent {

    val homeStack: Value<ChildStack<*, HomeTabDestinationChild>>

    sealed interface HomeTabDestinationChild {
        data class Home(val component: HomeComponent) : HomeTabDestinationChild
        data class CardSettings(val component: CardSettingsComponent) : HomeTabDestinationChild
        data class MoneyTransfer(val component: MoneyTransferComponent) : HomeTabDestinationChild
        data class TransferPreview(val component: TransferPreviewComponent) : HomeTabDestinationChild
        data class TransferConfirmation(val component: ConfirmTransferComponent) : HomeTabDestinationChild
        data class TransferSuccess(val component: TransferSuccessComponent) : HomeTabDestinationChild
        data class PayBills(val component: PayBillsComponent) : HomeTabDestinationChild
        data class BankTransfer(val component: BankTransferComponent) : HomeTabDestinationChild
    }
}