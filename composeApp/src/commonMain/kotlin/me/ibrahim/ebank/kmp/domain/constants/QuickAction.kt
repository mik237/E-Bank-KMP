package me.ibrahim.ebank.kmp.domain.constants

import androidx.compose.ui.graphics.Color
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.ic_bank
import e_bank_kmp.composeapp.generated.resources.ic_money_transfer
import e_bank_kmp.composeapp.generated.resources.ic_pay_bill
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Green
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import org.jetbrains.compose.resources.DrawableResource

sealed class QuickAction(
    val title: String,
    val icon: DrawableResource,
    val iconColor: Color,
) {
    data object MoneyTransfer : QuickAction(
        title = "Money Transfer",
        icon = Res.drawable.ic_money_transfer,
        iconColor = Color.ThemeColor_Green
    )

    data object PayBill : QuickAction(
        title = "Pay Bill",
        icon = Res.drawable.ic_pay_bill,
        iconColor = Color.ThemeColor_Blue
    )

    data object BankToBank : QuickAction(
        title = "Bank to Bank",
        icon = Res.drawable.ic_bank,
        iconColor = Color.ThemeColor_Grey
    )

    companion object {
        fun toList(): List<QuickAction> {
            return listOf(MoneyTransfer, PayBill, BankToBank)
        }
    }
}
