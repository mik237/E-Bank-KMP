package me.ibrahim.ebank.kmp.presentation.ui.home.quickActions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.ibrahim.ebank.kmp.domain.constants.QuickAction
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.getScreenWidth
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuickActionsUI(modifier: Modifier = Modifier, onClick: (QuickAction) -> Unit) {

    val size = getScreenWidth() / 3f

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {

        Text(
            "Quick Actions",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                color = Color.ThemeColor_DarkGrey,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(QuickAction.toList(), key = { it.title }) { quickAction ->
                Box(
                    modifier = Modifier
                        .size(size)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = false,
                            spotColor = Color.ThemeColor_Grey
                        ).background(Color.White, shape = RoundedCornerShape(10.dp)) // Rounded corners
                        .clickable { onClick(quickAction) }

                ) {
                    Column(
                        modifier = Modifier.matchParentSize().padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Icon(
                            painter = painterResource(quickAction.icon),
                            contentDescription = null,
                            modifier = Modifier.size(size / 2.5f)
                                .background(
                                    color = quickAction.iconColor.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(10.dp)
                                ).padding(10.dp),
                            tint = quickAction.iconColor
                        )
                        Text(
                            text = quickAction.title,
                            style = TextStyle(
                                color = Color.ThemeColor_DarkGrey,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}