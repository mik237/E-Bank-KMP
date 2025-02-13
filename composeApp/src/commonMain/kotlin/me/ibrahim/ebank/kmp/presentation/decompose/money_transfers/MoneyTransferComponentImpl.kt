package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransferState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUiAction

class MoneyTransferComponentImpl(val onBackClick: () -> Unit) : MoneyTransferComponent, InstanceKeeper.Instance {

    private val _state = MutableValue(MoneyTransferState())

    override val state: Value<MoneyTransferState>
        get() = _state

    override fun onAction(action: MoneyTransfersUiAction) {
        when (action) {
            MoneyTransfersUiAction.OnBackClick -> onBackClick()
            is MoneyTransfersUiAction.OnSearch -> _state.update { it.copy(searchKey = action.searchKey) }
        }
    }

    init {
        createRecentTransfersList()
    }

    private fun createRecentTransfersList() {
        val recentTransfers = listOf(
            RecentTransfer(
                "Dr. Kamal",
                "AED 140.00",
                "",
                "https://randomuser.me/api/portraits/men/31.jpg"
            ),
            RecentTransfer(
                "Jonathan",
                "AED 200.00",
                "",
                "https://randomuser.me/api/portraits/men/10.jpg"
            ),
            RecentTransfer(
                "Will Hopper",
                "AED 500.00",
                "",
                "https://randomuser.me/api/portraits/men/21.jpg"
            ),
            RecentTransfer(
                "Dr Strange",
                "AED 350.00",
                "",
                "https://randomuser.me/api/portraits/men/12.jpg"
            )
        )
        _state.update { it.copy(recentTransfers = recentTransfers) }
    }
}