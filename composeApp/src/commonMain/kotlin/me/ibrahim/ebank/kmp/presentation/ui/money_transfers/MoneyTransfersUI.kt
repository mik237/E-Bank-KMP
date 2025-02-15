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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import e_bank_kmp.composeapp.generated.resources.`continue`
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.money_transfers
import e_bank_kmp.composeapp.generated.resources.recent_transfers
import e_bank_kmp.composeapp.generated.resources.search
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.composables.EnterAmountScreen
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.decompose.money_transfers.MoneyTransferComponent
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.actions.MoneyTransfersUiAction
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.composables.RecentTransfersUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.states.MoneyTransferUiState
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyTransfersUI(component: MoneyTransferComponent) {

    val state by component.state.subscribeAsState()

    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state.uiState) {
        if (state.uiState == MoneyTransferUiState.Continue && state.currentCard != null && state.recipientInfo != null) {
            //Move to TransferPreviewUI
            component.onAction(
                MoneyTransfersUiAction.OnContinue(
                    state.currentCard!!, state.recipientInfo!!, state.amount
                )
            )
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            EnterAmountScreen(scaffoldState) { amount ->
                amount?.let {
                    component.onAction(MoneyTransfersUiAction.OnAmountSelected(it))
                }
                println("BottomSheet_state: card: ${state.currentCard?.balance} Amount: $amount, To: ${state.recipientInfo?.name}")
            }
        },
        sheetDragHandle = {},
        sheetTonalElevation = 0.dp,
        sheetShadowElevation = 15.dp
    ) {
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
                        .verticalScroll(state = rememberScrollState())
                        .imePadding(),
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

                    // Search Bar
                    OutlinedTextField(
                        value = state.searchKey,
                        onValueChange = { component.onAction(MoneyTransfersUiAction.OnSearch(it)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search, contentDescription = stringResource(Res.string.search),
                                tint = Color.ThemeColor_Grey
                            )
                        },
                        placeholder = {
                            Text(
                                stringResource(Res.string.search),
                                style = TextStyle(
                                    color = Color.ThemeColor_Grey,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.ThemeColor_Blue,
                            unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                            unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                            focusedContainerColor = Color.White
                        )
                    )

                    if (state.uiState is MoneyTransferUiState.Error) {
                        // Choose Reciepent Error
                        Text(
                            text = "** Choose Recipient",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start,
                            color = Color.ThemeColor_Red,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        )
                    }
                    // Recent Transfers
                    Text(
                        text = stringResource(Res.string.recent_transfers),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                    )

                    // Recent Transfers list
                    RecentTransfersUI(recipientInfos = state.recipientInfos) {
                        component.onAction(MoneyTransfersUiAction.OnRecentTransferClick(it))
                    }


                    // Make New Transfer Section
                    Text(
                        text = "Make new transfer",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.ThemeColor_Blue,
                                unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Enter Account Number") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.ThemeColor_Blue,
                                unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Reciever's Mobile Number") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.ThemeColor_Blue,
                                unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Purpose of payment (Optional)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.ThemeColor_Blue,
                                unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Pasword") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.ThemeColor_Blue,
                                unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White
                            ),
                            trailingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Password")
                                }
                            }
                        )


                        CustomButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .height(55.dp)
                                .padding(horizontal = 16.dp),
                            text = stringResource(Res.string.`continue`),
                            onClick = {
                                coroutineScope.launch {
                                    if (bottomSheetState.currentValue != SheetValue.Expanded) {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            },
                            containerColor = Color.ThemeColor_Blue,
                            contentColor = Color.White
                        )
                    }
                }
            }
        }
    }
}