package me.ibrahim.ebank.kmp.presentation.decompose.money_transfers

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.RecipientInfo
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.MoneyTransferState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.MoneyTransferUiState
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.MoneyTransfersUiAction

class MoneyTransferComponentImpl(
    card: Card, val onBackClick: () -> Unit,
    val onContinue: (card: Card, recipientInfo: RecipientInfo, amount: Double) -> Unit
) : MoneyTransferComponent, InstanceKeeper.Instance {

    private val _state = MutableValue(MoneyTransferState(currentCard = card))

    override val state: Value<MoneyTransferState>
        get() = _state

    override fun onAction(action: MoneyTransfersUiAction) {
        when (action) {
            MoneyTransfersUiAction.OnBackClick -> onBackClick()
            is MoneyTransfersUiAction.OnSearch -> _state.update { it.copy(searchKey = action.searchKey) }
            is MoneyTransfersUiAction.OnRecentTransferClick -> _state.update {
                it.copy(
                    recipientInfo = action.recipientInfo,
                    uiState = MoneyTransferUiState.Default
                )
            }

            is MoneyTransfersUiAction.OnAmountSelected -> {
                _state.update {
                    it.copy(
                        amount = action.amount,
                        uiState = if (state.value.recipientInfo == null) MoneyTransferUiState.Error() else MoneyTransferUiState.Continue
                    )
                }
            }

            is MoneyTransfersUiAction.OnCardSelected -> _state.update { it.copy(currentCard = action.card) }
            is MoneyTransfersUiAction.OnContinue -> onContinue(action.card, action.recipientInfo, action.amount)
        }
    }

    init {
        createRecentTransfersList()
    }

    private fun createRecentTransfersList() {
        val recipientInfos = listOf(
            RecipientInfo(
                "Dr. Kamal",
                "AED 140.00",
                "",
                "https://randomuser.me/api/portraits/men/1.jpg",
                accountNumber = "1234 5678 9101 1121"
            ),
            RecipientInfo(
                "Jonathan",
                "AED 200.00",
                "",
                "https://randomuser.me/api/portraits/men/4.jpg",
                accountNumber = "1234 5678 9101 4356"
            ),
            RecipientInfo(
                "Will Hopper",
                "AED 500.00",
                "",
                "https://randomuser.me/api/portraits/men/9.jpg",
                accountNumber = "1234 5678 9101 3425"
            ),
            RecipientInfo(
                "Dr Strange",
                "AED 350.00",
                "",
                "https://randomuser.me/api/portraits/men/29.jpg",
                accountNumber = "1234 5678 9101 4132"
            )
        )
        _state.update { it.copy(recipientInfos = recipientInfos) }
    }
}