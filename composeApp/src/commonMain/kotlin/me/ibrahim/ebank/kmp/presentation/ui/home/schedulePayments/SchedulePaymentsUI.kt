package me.ibrahim.ebank.kmp.presentation.ui.home.schedulePayments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.next_payment
import e_bank_kmp.composeapp.generated.resources.schedule_payments
import e_bank_kmp.composeapp.generated.resources.view_all
import me.ibrahim.ebank.kmp.domain.models.SchedulePayment
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SchedulePaymentsUI(modifier: Modifier = Modifier, payments: List<SchedulePayment>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(Res.string.schedule_payments),
                style = TextStyle(
                    color = Color.ThemeColor_DarkGrey,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            TextButton(onClick = {}, shape = CircleShape) {
                Text(
                    stringResource(Res.string.view_all),
                    style = TextStyle(
                        color = Color.ThemeColor_Grey,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }


        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(payments, key = { it.receiverName }) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = false,
                            spotColor = Color.ThemeColor_Grey
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(it.icon),
                            contentDescription = it.receiverName
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = it.receiverName,
                                style = TextStyle(
                                    color = Color.ThemeColor_DarkGrey,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )

                            Text(
                                text = stringResource(Res.string.next_payment, it.scheduleDate),
                                style = TextStyle(
                                    color = Color.ThemeColor_Grey,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                        Text(
                            text = "AED ${it.amount}",
                            style = TextStyle(
                                color = Color.ThemeColor_DarkGrey,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}