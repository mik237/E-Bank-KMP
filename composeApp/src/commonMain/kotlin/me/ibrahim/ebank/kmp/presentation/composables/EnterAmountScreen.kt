@file:OptIn(ExperimentalMaterial3Api::class)

package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.enter_amount
import e_bank_kmp.composeapp.generated.resources.quick_actions
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnterAmountScreen(scaffoldState: BottomSheetScaffoldState, onAmountSelected: (Double?) -> Unit) {

    var amount by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { coroutineScope.launch { scaffoldState.bottomSheetState.hide() } }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
            }
        }


        // Enter Amount
        Text(
            text = stringResource(Res.string.enter_amount),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = "AED $amount",
            onValueChange = {
            },
            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            readOnly = false,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.ThemeColor_Blue,
                unfocusedBorderColor = Color.ThemeColor_Blue
            )
        )

        // Quick Actions
        Text(
            text = stringResource(Res.string.quick_actions),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textAlign = TextAlign.Start
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            SuggestionChip(
                onClick = { amount = "100" },
                label = {
                    Text(
                        "AED 100",
                        style = TextStyle(
                            color = Color.ThemeColor_Grey,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.weight(1f)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            )
            SuggestionChip(
                onClick = { amount = "150" },
                label = {
                    Text(
                        "AED 150",
                        style = TextStyle(
                            color = Color.ThemeColor_Grey,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.weight(1f)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            )
            SuggestionChip(
                onClick = { amount = "200" },
                label = {
                    Text(
                        "AED 200",
                        style = TextStyle(
                            color = Color.ThemeColor_Grey,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.weight(1f)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            )
        }

        // Next Button
        Button(
            onClick = {
                onAmountSelected(amount.toDoubleOrNull())
                coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.ThemeColor_Blue)
        ) {
            Text("Next", color = Color.White)
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("1", "2", "3").map { title ->
                // Create an interaction source to track the pressed state
                val interactionSource = remember { MutableInteractionSource() }
                // Update colors based on the interaction state
                val borderColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)
                val containerColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.White else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)


                Button(
                    onClick = {
                        amount += title
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                        color = if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.Gray
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("4", "5", "6").map { title ->
                // Create an interaction source to track the pressed state
                val interactionSource = remember { MutableInteractionSource() }
                // Update colors based on the interaction state
                val borderColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)
                val containerColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.White else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)


                Button(
                    onClick = {
                        amount += title
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                        color = if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.Gray
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("7", "8", "9").map { title ->
                // Create an interaction source to track the pressed state
                val interactionSource = remember { MutableInteractionSource() }
                // Update colors based on the interaction state
                val borderColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)
                val containerColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.White else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)


                Button(
                    onClick = {
                        amount += title
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .size(80.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                        color = if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.Gray
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("C", "0", "Next").map { title ->
                // Create an interaction source to track the pressed state
                val interactionSource = remember { MutableInteractionSource() }
                // Update colors based on the interaction state
                val borderColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)
                val containerColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.White else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)


                Button(
                    onClick = {
                        when (title) {
                            "C" -> {
                                if (amount.isNotEmpty()) {
                                    amount = amount.dropLast(1)
                                }
                            }

                            "Next" -> {
                                onAmountSelected(amount.toDoubleOrNull())
                                coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
                            }

                            else -> {
                                amount += title
                            }
                        }
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .size(80.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                        color = if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.Gray
                    )
                }
            }
        }
    }
}