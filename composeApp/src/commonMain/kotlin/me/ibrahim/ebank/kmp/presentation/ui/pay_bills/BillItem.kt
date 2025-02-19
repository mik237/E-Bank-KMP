package me.ibrahim.ebank.kmp.presentation.ui.pay_bills

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BillItem(
    label: String,
    icon: DrawableResource,
    iconTintColor: Color,
    isSelected: Boolean,
    onBillSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 16.dp)
            .shadow(elevation = 15.dp, spotColor = Color.ThemeColor_LightGrey.copy(alpha = 0.3f))
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { onBillSelected() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier
                    .background(
                        color = iconTintColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(10.dp)
                    ).padding(7.dp),
                tint = iconTintColor
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
                onClick = onBillSelected,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.ThemeColor_Blue,
                    unselectedColor = Color.ThemeColor_Blue
                )
            )
        }
    }
}