package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.confirmation
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.ic_transfer_success
import e_bank_kmp.composeapp.generated.resources.transfer_success_message
import e_bank_kmp.composeapp.generated.resources.transfer_successful
import e_bank_kmp.composeapp.generated.resources.view_receipt
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.TransferSuccessComponent
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.TransferSuccessUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.composables.TransferRecieptUI
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferSuccessUI(component: TransferSuccessComponent) {

    val state by component.state.subscribeAsState()

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetDragHandle = {},
        containerColor = Color.Transparent,
        sheetContainerColor = Color.Transparent,
        contentColor = Color.Transparent,
        sheetContentColor = Color.Transparent,
        sheetPeekHeight = 0.dp,
        sheetShadowElevation = 0.dp,
        sheetShape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp),
        sheetContent = {
            TransferRecieptUI(state = state)
        }
    ) {
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
                            onClick = { component.onAction(TransferSuccessUiAction.OnBackClick) },
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
                    text = stringResource(Res.string.transfer_successful),
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
                    text = stringResource(Res.string.transfer_success_message),
                    fontSize = 15.sp,
                    color = Color.ThemeColor_Grey,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 0.dp, bottom = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(Res.drawable.ic_transfer_success),
                    contentDescription = stringResource(Res.string.transfer_successful),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(55.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(Res.string.view_receipt),
                    onClick = {
                        coroutineScope.launch {
                            if (scaffoldState.bottomSheetState.currentValue != SheetValue.Expanded) {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    },
                    containerColor = Color.ThemeColor_Blue,
                    contentColor = Color.White
                )
                Spacer(modifier = Modifier.weight(0.2f))
            }
        }
    }
}