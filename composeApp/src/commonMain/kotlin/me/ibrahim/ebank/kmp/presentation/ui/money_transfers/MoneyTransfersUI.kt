package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.money_transfers
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoneyTransfersUI(component: MoneyTransferComponent) {

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CustomAppBar(
                    title = stringResource(Res.string.money_transfers),
                    modifier = Modifier.statusBarsPadding(),
                    leadingIcon = {
                        IconButton(
                            onClick = { component.onAction(MoneyTransfersUiAction.OnBackClick) },
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
            }
        }
    }
}