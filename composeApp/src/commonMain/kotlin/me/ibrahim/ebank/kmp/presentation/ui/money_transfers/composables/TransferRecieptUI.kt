package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.transfer_fee
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.TransferPreviewState
import me.ibrahim.ebank.kmp.utils.ThemeColor_Green
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import me.ibrahim.ebank.kmp.utils.maskAccountNumber
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransferRecieptUI(state: TransferPreviewState) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 0.dp)
                .padding(top = 10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.ThemeColor_LightGrey)
                    ),
                    shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.TopCenter)
                .padding(horizontal = 0.dp)
                .padding(top = 32.dp),
            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = state.recipientInfo.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(text = maskAccountNumber(state.recipientInfo.accountNumber), color = Color.Gray)

                Text(
                    text = "Transaction Status: Success",
                    color = Color.ThemeColor_Green,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .background(
                            color = Color.ThemeColor_Green.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    text = "AED ${state.amount}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                TransferDetailRow("Card Type", "Debit Card")
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color.ThemeColor_LightGrey.copy(alpha = 0.5f)
                )
                TransferDetailRow(stringResource(Res.string.transfer_fee), "AED 0.00")
            }
        }

        AsyncImage(
            model = state.recipientInfo.imageUrl,
            contentDescription = state.recipientInfo.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    }
}
