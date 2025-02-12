package me.ibrahim.ebank.kmp.presentation.ui.home.quickServices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.services
import me.ibrahim.ebank.kmp.domain.constants.QuickServices
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.utils.getScreenWidth
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuickServicesUI(modifier: Modifier = Modifier) {

    val size = getScreenWidth() / 5f

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {

        Text(
            stringResource(Res.string.services),
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
            items(QuickServices.toList(), key = { it.id }) { service ->
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(
                                color = Color.ThemeColor_Grey.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(service.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size / 2.5f),
                            tint = service.iconColor
                        )
                    }

                    Text(
                        text = stringResource(service.title),
                        style = TextStyle(
                            color = Color.ThemeColor_Grey,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}