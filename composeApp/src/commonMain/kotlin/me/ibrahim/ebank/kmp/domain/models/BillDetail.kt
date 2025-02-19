package me.ibrahim.ebank.kmp.domain.models


import me.ibrahim.ebank.kmp.domain.constants.BillType

data class BillDetail(
    val companyName: String = "",
    val billNumber: String = "",
    val dueDate: String = "",
    val amount: Double = 0.0,
    val billType: BillType = BillType.INTERNET
)
