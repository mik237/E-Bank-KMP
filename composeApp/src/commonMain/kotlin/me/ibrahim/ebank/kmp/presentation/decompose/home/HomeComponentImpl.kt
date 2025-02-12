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
import e_bank_kmp.composeapp.generated.resources.ic_bank
import e_bank_kmp.composeapp.generated.resources.ic_money_transfer
import e_bank_kmp.composeapp.generated.resources.ic_netflix
import e_bank_kmp.composeapp.generated.resources.ic_pay_bill
import e_bank_kmp.composeapp.generated.resources.ic_paypal
import e_bank_kmp.composeapp.generated.resources.ic_spotify
import e_bank_kmp.composeapp.generated.resources.ic_visa
import me.ibrahim.ebank.kmp.domain.models.Card
import me.ibrahim.ebank.kmp.domain.models.QuickAction
import me.ibrahim.ebank.kmp.domain.models.SchedulePayment
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageAction
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageState
import me.ibrahim.ebank.kmp.utils.CardType
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Green
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey

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
        createSchedulePayments()
    }

    private fun createSchedulePayments() {
        val schedulePayments = listOf(
            SchedulePayment(
                scheduleDate = "12/04",
                receiverName = "Netflix",
                amount = 125.0,
                icon = Res.drawable.ic_netflix
            ),
            SchedulePayment(
                scheduleDate = "14/03",
                receiverName = "Paypal",
                amount = 3.50,
                icon = Res.drawable.ic_paypal
            ),
            SchedulePayment(
                scheduleDate = "25/03",
                receiverName = "Spotify",
                amount = 101.0,
                icon = Res.drawable.ic_spotify
            ),
            SchedulePayment(
                scheduleDate = "15/02",
                receiverName = "Credit Card",
                amount = 650.0,
                icon = Res.drawable.ic_visa
            )
        )

        _state.update { it.copy(schedulePayments = schedulePayments) }
    }

    private fun createQuickActionsList() {
        val actions = listOf(
            QuickAction(
                title = "Money Transfer",
                icon = Res.drawable.ic_money_transfer,
                iconColor = Color.ThemeColor_Green
            ),
            QuickAction(
                title = "Pay Bill",
                icon = Res.drawable.ic_pay_bill,
                iconColor = Color.ThemeColor_Blue
            ),
            QuickAction(
                title = "Bank to Bank",
                icon = Res.drawable.ic_bank,
                iconColor = Color.ThemeColor_Grey
            ),
        )
        _state.update { it.copy(quickActions = actions) }
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