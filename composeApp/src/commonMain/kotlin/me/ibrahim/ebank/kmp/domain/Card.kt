package me.ibrahim.ebank.kmp.domain

import me.ibrahim.ebank.kmp.utils.CardType
import org.jetbrains.compose.resources.DrawableResource

data class Card(
    val balance: Double,
    val cardHolderName: String,
    val cardType: CardType = CardType.MASTER,
    val validFrom: String,
    val validThru: String,
    val cardNumber: String,
    val cardImage: DrawableResource
)
