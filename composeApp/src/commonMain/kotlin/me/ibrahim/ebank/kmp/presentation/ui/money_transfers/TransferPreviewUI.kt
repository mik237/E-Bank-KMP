package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.avatar
import e_bank_kmp.composeapp.generated.resources.card
import e_bank_kmp.composeapp.generated.resources.card_blue
import e_bank_kmp.composeapp.generated.resources.confirmation
import e_bank_kmp.composeapp.generated.resources.`continue`
import e_bank_kmp.composeapp.generated.resources.ic_edit
import e_bank_kmp.composeapp.generated.resources.ic_forward_arrow
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.recipient
import e_bank_kmp.composeapp.generated.resources.total
import e_bank_kmp.composeapp.generated.resources.transfer_amount
import e_bank_kmp.composeapp.generated.resources.transfer_detail
import e_bank_kmp.composeapp.generated.resources.transfer_fee
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferPreviewComponent
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransferPreviewUI(component: TransferPreviewComponent) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
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
                        onClick = { component.onAction(TransferPreviewUiAction.OnBackClick) },
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

            // Recent Transfers
            Text(
                text = stringResource(Res.string.recipient),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            // Recipient Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.avatar), //transfer.imageUrl,
                            contentDescription = "transfer.name",
                            modifier = Modifier.clip(CircleShape)
                                .size(64.dp)
                                .background(Color.Gray),
                            contentScale = ContentScale.Crop,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = "Jonathan", fontWeight = FontWeight.Bold)
                            Text(text = "1******6103", color = Color.Gray)
                        }
                    }

                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(Res.drawable.ic_edit), contentDescription = "Edit")
                    }
                }
            }


            // Card
            Text(
                text = stringResource(Res.string.card),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp)
            )

            // Card Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(Res.drawable.card_blue), // Replace with your actual image resource
                        contentDescription = "Debit Card",
                        modifier = Modifier
                            .height(height = 50.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "Debit Card", fontWeight = FontWeight.Bold)
                        Text(text = "Master Card", color = Color.Gray)
                    }

                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(Res.drawable.ic_forward_arrow), contentDescription = "Details", tint = Color.ThemeColor_Blue)
                    }
                }
            }

            // Transfer Detail
            Text(
                text = stringResource(Res.string.transfer_detail),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TransferDetailRow(stringResource(Res.string.transfer_amount), "$250.00 USD")
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color.ThemeColor_LightGrey.copy(alpha = 0.5f)
                    )
                    TransferDetailRow(stringResource(Res.string.transfer_fee), "$0.00 USD")
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color.ThemeColor_LightGrey.copy(alpha = 0.5f)
                    )
                    TransferDetailRow(stringResource(Res.string.total), "$250.00 USD")
                }
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(55.dp)
                    .padding(horizontal = 16.dp),
                text = stringResource(Res.string.`continue`),
                onClick = {},
                containerColor = Color.ThemeColor_Blue,
                contentColor = Color.White
            )
        }
    }
}

@Composable
fun TransferDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 16.sp)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}