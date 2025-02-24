package me.ibrahim.ebank.kmp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.ic_analytics
import e_bank_kmp.composeapp.generated.resources.ic_home
import e_bank_kmp.composeapp.generated.resources.ic_locations
import e_bank_kmp.composeapp.generated.resources.ic_other
import e_bank_kmp.composeapp.generated.resources.ic_scanner
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.BottomNavComponent
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot
import me.ibrahim.ebank.kmp.presentation.ui.bank_transfer.BankTransferUI
import me.ibrahim.ebank.kmp.presentation.ui.card_settings.CardSettingsUI
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePage
import me.ibrahim.ebank.kmp.presentation.ui.login.LoginPage
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.MoneyTransfersUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferConfirmationUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferPreviewUI
import me.ibrahim.ebank.kmp.presentation.ui.money_transfers.TransferSuccessUI
import me.ibrahim.ebank.kmp.presentation.ui.onboarding.OnBoardingPage
import me.ibrahim.ebank.kmp.presentation.ui.pay_bills.PayBillsUI
import me.ibrahim.ebank.kmp.presentation.ui.signup.SignupPage
import me.ibrahim.ebank.kmp.presentation.ui.splash.SplashPage
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Red
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun EBankApp(root: BottomNavComponent) {

    val scope = rememberCoroutineScope()

    val pages by root.pages.subscribeAsState()
    val pagerState = rememberPagerState(pageCount = { root.configs.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            println("Current Page: Index = $it")
            root.onNewPageSelected(it)
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

                    is BottomNavComponent.BottomNavChild.HomeRoot -> RootContent(page.component)

                    BottomNavComponent.BottomNavChild.Scanner -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Blue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Scanner")
                        }
                    }

                    BottomNavComponent.BottomNavChild.Profile -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Green),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Profile")
                        }

                    }

                    BottomNavComponent.BottomNavChild.Locations -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.ThemeColor_Red),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Locations")
                        }
                    }

                    BottomNavComponent.BottomNavChild.Analytics -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(color = Color.Yellow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Analytics")
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


@Composable
fun RootContent(root: EBankRoot) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = root.backStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is EBankRoot.MainDestinationChild.Splash -> SplashPage(child.component)
            is EBankRoot.MainDestinationChild.OnBoarding -> OnBoardingPage(child.component)
            is EBankRoot.MainDestinationChild.Login -> LoginPage(child.component)
            is EBankRoot.MainDestinationChild.Signup -> SignupPage(child.component)
            is EBankRoot.MainDestinationChild.Home -> HomePage(child.component)
            is EBankRoot.MainDestinationChild.CardSettings -> CardSettingsUI(child.component)
            is EBankRoot.MainDestinationChild.MoneyTransfer -> MoneyTransfersUI(child.component)
            is EBankRoot.MainDestinationChild.TransferPreview -> TransferPreviewUI(child.component)
            is EBankRoot.MainDestinationChild.TransferConfirmation -> TransferConfirmationUI(child.component)
            is EBankRoot.MainDestinationChild.TransferSuccess -> TransferSuccessUI(child.component)
            is EBankRoot.MainDestinationChild.PayBills -> PayBillsUI(child.component)
            is EBankRoot.MainDestinationChild.BankTransfer -> BankTransferUI(child.component)
            is EBankRoot.MainDestinationChild.BottomNavBar -> {}
        }
    }
}

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    onTabSelected: (index: Int) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val bottomTabs by remember {
        mutableStateOf(
            listOf(
                BottomTabData("Home", Res.drawable.ic_home),
                BottomTabData("Locations", Res.drawable.ic_locations),
                BottomTabData("Scanner", Res.drawable.ic_scanner),
                BottomTabData("Analytics", Res.drawable.ic_analytics),
                BottomTabData("Profile", Res.drawable.ic_other),
            )
        )
    }
    BottomAppBar(modifier = modifier,
        containerColor = Color.White,
        contentPadding = PaddingValues(0.dp),
        actions = {
            bottomTabs.forEachIndexed { index, bottomTabData ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(bottomTabData.icon),
                            contentDescription = null
                        )
                    },
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                        onTabSelected(index)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.ThemeColor_Blue,
                        unselectedIconColor = Color.ThemeColor_LightGrey,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        })
}

data class BottomTabData(
    val title: String,
    val icon: DrawableResource
)


