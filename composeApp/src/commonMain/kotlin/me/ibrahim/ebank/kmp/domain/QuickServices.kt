package me.ibrahim.ebank.kmp.domain

import androidx.compose.ui.graphics.Color
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.charity
import e_bank_kmp.composeapp.generated.resources.gifts
import e_bank_kmp.composeapp.generated.resources.ic_charity
import e_bank_kmp.composeapp.generated.resources.ic_gifts
import e_bank_kmp.composeapp.generated.resources.ic_insurance
import e_bank_kmp.composeapp.generated.resources.ic_loan
import e_bank_kmp.composeapp.generated.resources.ic_recharge
import e_bank_kmp.composeapp.generated.resources.insurance
import e_bank_kmp.composeapp.generated.resources.loan
import e_bank_kmp.composeapp.generated.resources.recharge
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class QuickServices(
    val id: Int,
    val title: StringResource,
    val icon: DrawableResource,
    val iconColor: Color
) {
    data object Recharge : QuickServices(
        1,
        title = Res.string.recharge,
        icon = Res.drawable.ic_recharge,
        iconColor = Color.ThemeColor_Blue
    )

    data object Charity : QuickServices(
        2,
        title = Res.string.charity,
        icon = Res.drawable.ic_charity,
        iconColor = Color.ThemeColor_Blue
    )

    data object Loan : QuickServices(
        3,
        title = Res.string.loan,
        icon = Res.drawable.ic_loan,
        iconColor = Color.ThemeColor_Blue
    )

    data object Gifts : QuickServices(
        4,
        title = Res.string.gifts,
        icon = Res.drawable.ic_gifts,
        iconColor = Color.ThemeColor_Blue
    )

    data object Insurance : QuickServices(
        5,
        title = Res.string.insurance,
        icon = Res.drawable.ic_insurance,
        iconColor = Color.ThemeColor_Blue
    )

    companion object {
        fun toList(): List<QuickServices> {
            return listOf(Recharge, Charity, Loan, Gifts, Insurance)
        }
    }
}