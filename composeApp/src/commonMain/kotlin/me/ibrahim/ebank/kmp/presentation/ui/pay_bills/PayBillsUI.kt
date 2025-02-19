package me.ibrahim.ebank.kmp.presentation.ui.pay_bills

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.company_name
import e_bank_kmp.composeapp.generated.resources.electricity_bills
import e_bank_kmp.composeapp.generated.resources.fill_details
import e_bank_kmp.composeapp.generated.resources.ic_electricity_bill
import e_bank_kmp.composeapp.generated.resources.ic_internet_bill
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.ic_other_bill
import e_bank_kmp.composeapp.generated.resources.ic_water_bills
import e_bank_kmp.composeapp.generated.resources.internet_bills
import e_bank_kmp.composeapp.generated.resources.next
import e_bank_kmp.composeapp.generated.resources.other_bills
import e_bank_kmp.composeapp.generated.resources.password
import e_bank_kmp.composeapp.generated.resources.pay_bill
import e_bank_kmp.composeapp.generated.resources.reference_number
import e_bank_kmp.composeapp.generated.resources.water_bills
import e_bank_kmp.composeapp.generated.resources.your_bills
import me.ibrahim.ebank.kmp.domain.constants.BillType
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.decompose.pay_bills.PayBillsComponent
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Green
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PayBillsUI(component: PayBillsComponent) {

    val state by component.state.subscribeAsState()

    var selectedBill by remember { mutableStateOf<BillType>(BillType.INTERNET) }
    var companyName by remember { mutableStateOf("") }
    var referenceNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }


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
                title = stringResource(Res.string.pay_bill),
                modifier = Modifier.statusBarsPadding(),
                leadingIcon = {
                    IconButton(
                        onClick = { component.onAction(PayBillsUiAction.OnBackClick) },
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

            // Your Bills
            Text(
                text = stringResource(Res.string.your_bills),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            BillItem(
                label = stringResource(Res.string.internet_bills),
                icon = Res.drawable.ic_internet_bill,
                iconTintColor = Color.ThemeColor_Green,
                isSelected = selectedBill == BillType.INTERNET,
                onBillSelected = { selectedBill = BillType.INTERNET }
            )
            BillItem(
                label = stringResource(Res.string.electricity_bills),
                icon = Res.drawable.ic_electricity_bill,
                iconTintColor = Color.ThemeColor_Red,
                isSelected = selectedBill == BillType.ELECTRICITY,
                onBillSelected = { selectedBill = BillType.ELECTRICITY }
            )
            BillItem(
                label = stringResource(Res.string.water_bills),
                icon = Res.drawable.ic_water_bills,
                iconTintColor = Color.ThemeColor_Blue,
                isSelected = selectedBill == BillType.WATER,
                onBillSelected = { selectedBill = BillType.WATER }
            )
            BillItem(
                label = stringResource(Res.string.other_bills),
                icon = Res.drawable.ic_other_bill,
                iconTintColor = Color.ThemeColor_Grey,
                isSelected = selectedBill == BillType.OTHER,
                onBillSelected = { selectedBill = BillType.OTHER }
            )


            // Fill Details
            Text(
                text = stringResource(Res.string.fill_details),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    placeholder = { Text(stringResource(Res.string.company_name)) },
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
                    value = referenceNumber,
                    onValueChange = { referenceNumber = it },
                    placeholder = { Text(stringResource(Res.string.reference_number)) },
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
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    placeholder = { Text(stringResource(Res.string.password)) },
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
                        IconButton(onClick = { }) {
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
                    text = stringResource(Res.string.next),
                    onClick = {
                        component.onAction(PayBillsUiAction.OnNextClick)
                    },
                    containerColor = Color.ThemeColor_Blue,
                    contentColor = Color.White
                )
            }
        }
    }
}