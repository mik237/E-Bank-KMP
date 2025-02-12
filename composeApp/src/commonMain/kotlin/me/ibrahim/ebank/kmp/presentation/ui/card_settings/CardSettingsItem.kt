package me.ibrahim.ebank.kmp.presentation.ui.card_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey

@Composable
fun CardSettingsItem(
    modifier: Modifier = Modifier,
    title: String,
    bgColor: Color = Color.White,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    leading: @Composable () -> Unit,
    trailing: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.ThemeColor_Grey.copy(alpha = 0.5f)
            )
            .background(
                color = bgColor,
                shape = RoundedCornerShape(10.dp)
            ).padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            leading()
            Text(
                text = title,
                style = titleStyle,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Start
            )
            trailing()
        }
    }
}