package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.ic_notifications
import e_bank_kmp.composeapp.generated.resources.uae_flag
import me.ibrahim.ebank.kmp.utils.StrokeGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomePageHeader(modifier: Modifier = Modifier, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            painter = painterResource(Res.drawable.uae_flag),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.ThemeColor_DarkGrey
            )
        )

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
}