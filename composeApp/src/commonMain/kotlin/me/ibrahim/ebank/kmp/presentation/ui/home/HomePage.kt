package me.ibrahim.ebank.kmp.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import me.ibrahim.ebank.kmp.presentation.composables.CardsPager
import me.ibrahim.ebank.kmp.presentation.composables.HomePageHeader
import me.ibrahim.ebank.kmp.presentation.composables.Indicators
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey

@Composable
fun HomePage(component: HomeComponent) {

    val state by component.state.subscribeAsState()

    val pagerState = rememberPagerState { state.cards.size }

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = state.uiState is HomePageUiState.Loading
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HomePageHeader(title = "Fintech", modifier = Modifier.statusBarsPadding())

                CardsPager(cards = state.cards, pagerState = pagerState)

                Indicators(
                    count = state.cards.size,
                    height = 5,
                    spacer = 5,
                    selectedColor = Color.ThemeColor_Blue,
                    unselectedColor = Color.ThemeColor_Grey,
                    selectedIndex = pagerState.currentPage,
                    selectedLength = 20,
                    modifier = Modifier
                )

                QuickActions(
                    actions = state.quickActions,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}
