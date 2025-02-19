package me.ibrahim.ebank.kmp.presentation.decompose.pay_bills

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.domain.models.BillDetail
import me.ibrahim.ebank.kmp.presentation.ui.pay_bills.PayBillsUiAction

class PayBillsComponentImpl(
    private val onBack: () -> Unit,
    private val onNext: () -> Unit
) : PayBillsComponent {

    private val _state = MutableValue(BillDetail())
    override val state: Value<BillDetail> = _state

    override fun onAction(action: PayBillsUiAction) {
        when (action) {
            PayBillsUiAction.OnBackClick -> onBack()
            PayBillsUiAction.OnNextClick -> onNext()
        }
    }
}