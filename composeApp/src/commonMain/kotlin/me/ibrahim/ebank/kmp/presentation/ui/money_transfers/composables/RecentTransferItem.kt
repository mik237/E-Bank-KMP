package me.ibrahim.ebank.kmp.presentation.ui.money_transfers.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey

@Composable
fun RecentTransferItem(transfer: RecentTransfer, currentIndex: Int, selectedIndex: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .wrapContentSize()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.ThemeColor_LightGrey
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                border = if (selectedIndex == currentIndex) BorderStroke(1.dp, Color.ThemeColor_Blue) else BorderStroke(0.dp, Color.Transparent),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp)
            .padding(horizontal = 15.dp)
            .clickable {
                onClick()
            }
    ) {

        AsyncImage(
            model = transfer.imageUrl,
            contentDescription = transfer.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop,
            onSuccess = {
                println("RecentTransfer: image loaded: $it")
            },
            onError = {
                println("RecentTransfer: image loaded: ${it.result.throwable.message}")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = transfer.name,
            style = TextStyle(
                color = Color.ThemeColor_LightGrey,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "${transfer.amount}${transfer.currency}",
            style = TextStyle(
                color = Color.ThemeColor_DarkGrey,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}