package me.ibrahim.ebank.kmp.presentation.ui.bank_transfer

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
import androidx.compose.material.icons.filled.Search
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
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.bank_transfer
import e_bank_kmp.composeapp.generated.resources.byline_bank
import e_bank_kmp.composeapp.generated.resources.`continue`
import e_bank_kmp.composeapp.generated.resources.ic_byline_bank
import e_bank_kmp.composeapp.generated.resources.ic_m_and_t_bank
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.ic_shamrock_bank
import e_bank_kmp.composeapp.generated.resources.ic_truist_bank
import e_bank_kmp.composeapp.generated.resources.m_and_t_bank
import e_bank_kmp.composeapp.generated.resources.next
import e_bank_kmp.composeapp.generated.resources.search
import e_bank_kmp.composeapp.generated.resources.search_bank
import e_bank_kmp.composeapp.generated.resources.shamrock_bank
import e_bank_kmp.composeapp.generated.resources.transfer_to_bank
import e_bank_kmp.composeapp.generated.resources.truist_financial
import me.ibrahim.ebank.kmp.presentation.composables.CustomAppBar
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.decompose.bank_transfer.BankTransferComponent
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BankTransferUI(component: BankTransferComponent) {

    var searchBank by remember { mutableStateOf("") }

    var selectedBank by remember { mutableStateOf("") }

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
                title = stringResource(Res.string.bank_transfer),
                modifier = Modifier.statusBarsPadding(),
                leadingIcon = {
                    IconButton(
                        onClick = { component.onAction(BankTransferUiAction.OnBackClick) },
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

            // Transfer to Bank
            Text(
                text = stringResource(Res.string.transfer_to_bank),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.ThemeColor_DarkGrey,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 0.dp)
            )

            Text(
                text = stringResource(Res.string.search_bank),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.ThemeColor_LightGrey,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 0.dp, bottom = 0.dp)
            )
            // Search Bar
            OutlinedTextField(
                value = searchBank,
                onValueChange = { searchBank = it },
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
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.ThemeColor_Blue,
                    unfocusedBorderColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                    unfocusedContainerColor = Color.ThemeColor_LightGrey.copy(alpha = 0.2f),
                    focusedContainerColor = Color.White
                )
            )
            val bylineBank = stringResource(Res.string.byline_bank)
            BankItem(
                label = bylineBank,
                icon = Res.drawable.ic_byline_bank,
                isSelected = selectedBank == bylineBank,
                onBankSelected = { selectedBank = bylineBank }
            )

            val shamrockBank = stringResource(Res.string.shamrock_bank)
            BankItem(
                label = shamrockBank,
                icon = Res.drawable.ic_shamrock_bank,
                isSelected = shamrockBank == selectedBank,
                onBankSelected = { selectedBank = shamrockBank }
            )

            val mAndTBank = stringResource(Res.string.m_and_t_bank)
            BankItem(
                label = mAndTBank,
                icon = Res.drawable.ic_m_and_t_bank,
                isSelected = selectedBank == mAndTBank,
                onBankSelected = { selectedBank = mAndTBank }
            )

            val truistFinancial = stringResource(Res.string.truist_financial)
            BankItem(
                label = truistFinancial,
                icon = Res.drawable.ic_truist_bank,
                isSelected = selectedBank == truistFinancial,
                onBankSelected = { selectedBank = truistFinancial }
            )

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
fun BankItem(
    label: String,
    icon: DrawableResource,
    isSelected: Boolean,
    onBankSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 16.dp)
            .shadow(elevation = 15.dp, spotColor = Color.ThemeColor_LightGrey.copy(alpha = 0.5f))
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { onBankSelected() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = label,
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color.ThemeColor_DarkGrey,
                    fontSize = 16.sp
                )
            )

            RadioButton(
                selected = isSelected,
                onClick = onBankSelected,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.ThemeColor_Blue,
                    unselectedColor = Color.ThemeColor_Blue
                )
            )
        }
    }
}