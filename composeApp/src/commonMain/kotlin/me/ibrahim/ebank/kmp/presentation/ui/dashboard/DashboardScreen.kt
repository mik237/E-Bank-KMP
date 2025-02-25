package me.ibrahim.ebank.kmp.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.presentation.composables.CustomBottomAppBar
import me.ibrahim.ebank.kmp.presentation.decompose.dashboard.DashboardComponent
import me.ibrahim.ebank.kmp.presentation.ui.dashboard.bottom_tabs.HomeTab
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red

@Composable
fun DashboardScreen(component: DashboardComponent) {

    val scope = rememberCoroutineScope()

    val pages by component.pages.subscribeAsState()
    val pagerState = rememberPagerState(pageCount = { component.configs.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            component.onNewPageSelected(it)
        }
    }
    MaterialTheme {
        Column {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White),
                state = pagerState,
                userScrollEnabled = false,
            ) {

                when (val page = pages.items[it].instance) {

                    is DashboardComponent.BottomNavChild.Home -> HomeTab(component = page.component)

                    DashboardComponent.BottomNavChild.Scanner -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Scanning Feature coming soon..",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }

                    DashboardComponent.BottomNavChild.Profile -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Green),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Profile coming soon..",
                                style = MaterialTheme.typography.titleLarge)
                        }

                    }

                    DashboardComponent.BottomNavChild.Locations -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.ThemeColor_Red),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Locations coming soon..",
                                style = MaterialTheme.typography.titleLarge)
                        }
                    }

                    DashboardComponent.BottomNavChild.Analytics -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Yellow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Analytics coming soon..",
                                style = MaterialTheme.typography.titleLarge)
                        }
                    }

                    else -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Magenta),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Else")
                        }
                    }
                }
            }

            CustomBottomAppBar {
                scope.launch { pagerState.scrollToPage(it) }
            }
        }
    }
}