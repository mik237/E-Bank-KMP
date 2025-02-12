package me.ibrahim.ebank.kmp.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class SchedulePayment(
    val scheduleDate: String,
    val receiverName: String,
    val amount: Double,
    val icon: DrawableResource
)
