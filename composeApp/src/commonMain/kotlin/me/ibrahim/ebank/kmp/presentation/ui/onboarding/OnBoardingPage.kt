package me.ibrahim.ebank.kmp.presentation.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.onboarding1
import e_bank_kmp.composeapp.generated.resources.onboarding2
import e_bank_kmp.composeapp.generated.resources.onboarding_desc_1
import e_bank_kmp.composeapp.generated.resources.onboarding_desc_2
import e_bank_kmp.composeapp.generated.resources.onboarding_title_1
import e_bank_kmp.composeapp.generated.resources.onboarding_title_2
import e_bank_kmp.composeapp.generated.resources.skip
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import me.ibrahim.ebank.kmp.presentation.composables.Indicators
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun OnBoardingPage(component: OnBoardingComponent) {
    val pageCount = 2
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.ThemeColor_DarkGrey)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
                    .weight(0.85f)
            ) { page ->
                val painter = painterResource(if (page == 0) Res.drawable.onboarding1 else Res.drawable.onboarding2)
                val title = stringResource(if (page == 0) Res.string.onboarding_title_1 else Res.string.onboarding_title_2)
                val description = stringResource(if (page == 0) Res.string.onboarding_desc_1 else Res.string.onboarding_desc_2)
                PageContent(
                    painter = painter,
                    title = title,
                    description = description
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .weight(0.15f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Indicators(
                    count = pageCount,
                    height = 5,
                    spacer = 5,
                    selectedColor = Color.White,
                    unselectedColor = Color.ThemeColor_Grey,
                    selectedIndex = pagerState.currentPage,
                    selectedLength = 60,
                    modifier = Modifier
                )

                Button(
                    onClick = { component.onSkipBoarding() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(45.dp)
                        .padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.ThemeColor_Grey,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.skip),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}