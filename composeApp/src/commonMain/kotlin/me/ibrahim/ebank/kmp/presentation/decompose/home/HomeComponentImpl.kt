package me.ibrahim.ebank.kmp.presentation.decompose.home

import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.card_black
import e_bank_kmp.composeapp.generated.resources.card_blue
import e_bank_kmp.composeapp.generated.resources.card_green
import e_bank_kmp.composeapp.generated.resources.ic_visa
import me.ibrahim.ebank.kmp.domain.Card
import me.ibrahim.ebank.kmp.domain.QuickAction
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
        createQuickActionsList()
    }

    private fun createQuickActionsList() {
        val actions = listOf(
            QuickAction(
                title = "",
                icon = Res.drawable.ic_visa,
                iconColor = Color.White
            )
        )
    }

    private fun createCardsList() {
        val cards = listOf(
            Card(
                cardNumber = "•••• •••• •••• 8635",
                cardType = CardType.MASTER,
                cardHolderName = "Will Jonas",
                balance = 4030.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_blue
            ),
            Card(
                cardNumber = "•••• •••• •••• 8635",
                cardType = CardType.VISA,
                cardHolderName = "Will Jonas",
                balance = 1430.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_green
            ),
            Card(
                cardNumber = "•••• •••• •••• 8635",
                cardType = CardType.MASTER,
                cardHolderName = "Will Jonas",
                balance = 24030.0,
                validFrom = "10/24",
                validThru = "10/30",
                cardImage = Res.drawable.card_black
            )
        )
        _state.update { it.copy(cards = cards) }
    }
}