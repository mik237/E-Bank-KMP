package me.ibrahim.ebank.kmp.presentation.decompose.home

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.card_black
import e_bank_kmp.composeapp.generated.resources.card_blue
import e_bank_kmp.composeapp.generated.resources.card_green
import me.ibrahim.ebank.kmp.domain.Card
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageAction
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageState
import me.ibrahim.ebank.kmp.utils.CardType

class HomeComponentImpl : HomeComponent, InstanceKeeper.Instance {

    private val _state = MutableValue(HomePageState())

    override val state: Value<HomePageState> = _state

    override fun onAction(action: HomePageAction) {
        when (action) {
            is HomePageAction.OnCardClick -> {}
        }
    }

    init {
        createCardsList()
    }

    private fun createCardsList() {
        val cards = listOf(
            Card(
                cardNumber = "",
                cardType = CardType.MASTER,
                cardHolderName = "Muhammad Ibrahim",
                balance = 4030.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_blue
            ),
            Card(
                cardNumber = "",
                cardType = CardType.MASTER,
                cardHolderName = "Muhammad Ibrahim",
                balance = 1430.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_green
            ),
            Card(
                cardNumber = "",
                cardType = CardType.MASTER,
                cardHolderName = "Muhammad Ibrahim",
                balance = 24030.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_black
            )
        )
        _state.update { it.copy(cards = cards) }
    }
}