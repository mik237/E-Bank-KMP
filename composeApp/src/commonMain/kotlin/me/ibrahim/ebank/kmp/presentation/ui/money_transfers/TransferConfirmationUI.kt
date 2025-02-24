package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.are_you_sure
import e_bank_kmp.composeapp.generated.resources.confirmation
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.send_money
import e_bank_kmp.composeapp.generated.resources.transfer_confirmation_message
import e_bank_kmp.composeapp.generated.resources.transfer_fee
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.ConfirmTransferComponent
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferConfirmationUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.composables.TransferDetailRow
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red
import me.ibrahim.ebank.kmp.utils.maskAccountNumber
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransferConfirmationUI(component: ConfirmTransferComponent) {

    val state by component.state.subscribeAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomAppBar(
                title = stringResource(Res.string.confirmation),
                modifier = Modifier.statusBarsPadding(),
                leadingIcon = {
                    IconButton(
                        onClick = { component.onAction(TransferConfirmationUiAction.OnBackClick) },
                        modifier = Modifier.size(40.dp)
                            .clip(CircleShape)
                            .background(color = Color.StrokeGrey.copy(alpha = 0.25f))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.ArrowBackIos,
                            contentDescription = null,
                            tint = Color.StrokeGrey
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(40.dp)
                            .clip(CircleShape)
                            .background(color = Color.StrokeGrey.copy(alpha = 0.25f))
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_notifications),
                            contentDescription = null,
                            tint = Color.StrokeGrey
                        )
                    }
                }
            )

            Text(
                text = stringResource(Res.string.are_you_sure),
                fontSize = 26.sp,
                color = Color.ThemeColor_Blue,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp, bottom = 8.dp)
            )

            Text(
                text = stringResource(Res.string.transfer_confirmation_message),
                fontSize = 15.sp,
                color = Color.ThemeColor_Grey,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 0.dp, bottom = 8.dp)
            )

            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = state.recipientInfo.imageUrl,
                    contentDescription = "transfer.name",
                    modifier = Modifier.clip(CircleShape)
                        .size(64.dp)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop,
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f))
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
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        Text(text = maskAccountNumber(state.recipientInfo.accountNumber), color = Color.Gray)

                        Text(
                            text = "Transaction Status: Pending",
                            color = Color.ThemeColor_Red,
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .background(
                                    color = Color.ThemeColor_Red.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        Text(
                            text = "AED ${state.amount}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
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
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(55.dp)
                    .padding(horizontal = 16.dp),
                text = stringResource(Res.string.send_money),
                onClick = {
                    component.onAction(TransferConfirmationUiAction.OnContinueClick)
                },
                containerColor = Color.ThemeColor_Blue,
                contentColor = Color.White
            )
        }
    }
}