package me.ibrahim.ebank.kmp.domain.models

data class RecipientInfo(
    val name: String,
    val amount: String,
    val currency: String,
    val imageUrl: String,
    val accountNumber: String
)
