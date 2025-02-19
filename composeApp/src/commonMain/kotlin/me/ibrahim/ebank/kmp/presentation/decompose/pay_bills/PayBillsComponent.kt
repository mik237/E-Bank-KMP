package me.ibrahim.ebank.kmp.presentation.decompose.pay_bills

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.BillDetail
import me.ibrahim.ebank.kmp.presentation.ui.pay_bills.PayBillsUiAction

interface PayBillsComponent {
    val state: Value<BillDetail>
    fun onAction(action: PayBillsUiAction)
}