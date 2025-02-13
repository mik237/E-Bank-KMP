package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnterAmountScreen() {

    var amount by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
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
                //Only update when the value is a number
                if (it.removePrefix("AED ").matches(Regex("^[0-9]*\$"))) {
                    amount = it.removePrefix("AED ")
                }
            },
            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(12.dp),
            readOnly = true,
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
                .padding(bottom = 24.dp)
        ) {
            SuggestionChip(onClick = { amount = "100" }, label = { Text("$100") })
            SuggestionChip(onClick = { amount = "150" }, label = { Text("$150") })
            SuggestionChip(onClick = { amount = "200" }, label = { Text("$200") })
        }

        // Next Button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6279F1))
        ) {
            Text("Next", color = Color.White)
        }

        // Numeric Keyboard
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            val buttons = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "0", "->")
            items(buttons.size) { index ->
                val button = buttons[index]

                // Create an interaction source to track the pressed state
                val interactionSource = remember { MutableInteractionSource() }
                // Update colors based on the interaction state
                val borderColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)
                val containerColor =
                    if (interactionSource.collectIsPressedAsState().value) Color.White else Color.ThemeColor_LightGrey.copy(alpha = 0.2f)


                Button(
                    onClick = {
                        when (button) {
                            "X" -> {
                                if (amount.isNotEmpty()) {
                                    amount = amount.dropLast(1)
                                }
                            }

                            "->" -> {
                                // Handle "Next" action from keyboard
                            }

                            else -> {
                                amount += button
                            }
                        }
                    },
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .width(5.dp)
                        .aspectRatio(1f),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = button,
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                        color = if (interactionSource.collectIsPressedAsState().value) Color.ThemeColor_Blue else Color.Gray
                    )
                }
            }
        }

        // Bottom Padding
        Spacer(modifier = Modifier.height(16.dp))
    }
}