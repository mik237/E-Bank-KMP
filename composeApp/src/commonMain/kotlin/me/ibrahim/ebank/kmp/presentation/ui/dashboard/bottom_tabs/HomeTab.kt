package me.ibrahim.ebank.kmp.presentation.ui.dashboard.bottom_tabs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab.HomeTabComponent
import me.ibrahim.ebank.kmp.presentation.ui.bank_transfer.BankTransferUI
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUI
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePage
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferConfirmationUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferPreviewUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferSuccessUI
import me.ibrahim.ebank.kmp.presentation.ui.pay_bills.PayBillsUI

@Composable
fun HomeTab(component: HomeTabComponent) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = component.homeStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is HomeTabComponent.HomeTabDestinationChild.BankTransfer -> BankTransferUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.CardSettings -> CardSettingsUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.Home -> HomePage(child.component)
            is HomeTabComponent.HomeTabDestinationChild.MoneyTransfer -> MoneyTransfersUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.PayBills -> PayBillsUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.TransferConfirmation -> TransferConfirmationUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.TransferPreview -> TransferPreviewUI(child.component)
            is HomeTabComponent.HomeTabDestinationChild.TransferSuccess -> TransferSuccessUI(child.component)
        }
    }
}