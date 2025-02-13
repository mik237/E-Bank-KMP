package me.ibrahim.ebank.kmp.domain.models

data class RecentTransfer(
    val name: String,
    val amount: String,
    val currency: String,
    val imageUrl: String
)
