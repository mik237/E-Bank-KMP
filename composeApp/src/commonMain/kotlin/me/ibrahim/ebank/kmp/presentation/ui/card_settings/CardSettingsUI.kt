package me.ibrahim.ebank.kmp.presentation.ui.card_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.active
import e_bank_kmp.composeapp.generated.resources.card_status
import e_bank_kmp.composeapp.generated.resources.change_pin
import e_bank_kmp.composeapp.generated.resources.credit_limit
import e_bank_kmp.composeapp.generated.resources.deactivate_card
import e_bank_kmp.composeapp.generated.resources.ic_change_pin
import e_bank_kmp.composeapp.generated.resources.ic_deactivate_card
import e_bank_kmp.composeapp.generated.resources.ic_forward_arrow
import e_bank_kmp.composeapp.generated.resources.ic_lock_card
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.lock_card
import e_bank_kmp.composeapp.generated.resources.my_card
import e_bank_kmp.composeapp.generated.resources.save
import e_bank_kmp.composeapp.generated.resources.settings
import me.ibrahim.ebank.kmp.presentation.composables.CardsPager
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.decompose.card_settings.CardSettingsComponent
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Green
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

@Composable
fun CardSettingsUI(component: CardSettingsComponent) {

    val state by component.state.subscribeAsState()

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
                    title = stringResource(Res.string.my_card),
                    modifier = Modifier.statusBarsPadding(),
                    leadingIcon = {
                        IconButton(
                            onClick = { component.onAction(CardSettingsUIAction.OnBackClick) },
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

                CardsPager(cards = listOf(state.selectedCard), horizontalPadding = 16.dp)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(top = 6.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(0.5f)
                            .height(90.dp)
                            .background(
                                color = Color.ThemeColor_Green.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            ).padding(15.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Text(
                                text = stringResource(Res.string.credit_limit),
                                style = TextStyle(
                                    color = Color.ThemeColor_Green,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "AED ${(round((state.selectedCard.balance / 11.0) * 100.0) / 100.0)}",
                                style = TextStyle(
                                    color = Color.ThemeColor_Green,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.weight(0.5f)
                            .height(90.dp)
                            .background(
                                color = Color.ThemeColor_Red.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            ).padding(15.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Text(
                                text = stringResource(Res.string.card_status),
                                style = TextStyle(
                                    color = Color.ThemeColor_Red,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = stringResource(Res.string.active),
                                style = TextStyle(
                                    color = Color.ThemeColor_Red,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }

                Text(
                    stringResource(Res.string.settings),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 6.dp),
                    style = TextStyle(
                        color = Color.ThemeColor_DarkGrey,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start
                    )
                )

                CardSettingsItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    title = stringResource(Res.string.change_pin),
                    leading = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_change_pin),
                            contentDescription = stringResource(Res.string.change_pin),
                            tint = Color.ThemeColor_Green
                        )
                    },
                    trailing = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_forward_arrow),
                            contentDescription = stringResource(Res.string.change_pin),
                            tint = Color.ThemeColor_Blue
                        )
                    })

                CardSettingsItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    title = stringResource(Res.string.lock_card),
                    leading = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_lock_card),
                            contentDescription = stringResource(Res.string.lock_card),
                            tint = Color.ThemeColor_Red
                        )
                    },
                    trailing = {
                        Switch(
                            modifier = Modifier.height(10.dp),
                            checked = state.lockCard,
                            onCheckedChange = { component.onAction(CardSettingsUIAction.LockCard(it)) },
                            colors = SwitchDefaults.colors(
                                uncheckedTrackColor = Color.ThemeColor_Grey.copy(alpha = 0.5f),
                                checkedTrackColor = Color.ThemeColor_Blue,
                                uncheckedThumbColor = Color.White,
                                checkedThumbColor = Color.White,
                                uncheckedBorderColor = Color.Transparent,
                                checkedBorderColor = Color.Transparent
                            )
                        )
                    })

                CardSettingsItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    title = stringResource(Res.string.deactivate_card),
                    leading = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_deactivate_card),
                            contentDescription = stringResource(Res.string.deactivate_card),
                            tint = Color.ThemeColor_Blue
                        )
                    },
                    trailing = {
                        Switch(
                            modifier = Modifier,
                            checked = state.deactivateCard,
                            onCheckedChange = { component.onAction(CardSettingsUIAction.DeActivateCard(it)) },
                            colors = SwitchDefaults.colors(
                                uncheckedTrackColor = Color.ThemeColor_Grey.copy(alpha = 0.5f),
                                checkedTrackColor = Color.ThemeColor_Blue,
                                uncheckedThumbColor = Color.White,
                                checkedThumbColor = Color.White,
                                uncheckedBorderColor = Color.Transparent,
                                checkedBorderColor = Color.Transparent
                            )
                        )
                    })

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(55.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(Res.string.save),
                    onClick = {},
                    containerColor = Color.ThemeColor_Blue,
                    contentColor = Color.White
                )
            }
        }
    }
}