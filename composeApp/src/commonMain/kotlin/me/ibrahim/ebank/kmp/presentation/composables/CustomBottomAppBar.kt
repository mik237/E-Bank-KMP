package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.ic_analytics
import e_bank_kmp.composeapp.generated.resources.ic_home
import e_bank_kmp.composeapp.generated.resources.ic_locations
import e_bank_kmp.composeapp.generated.resources.ic_other
import e_bank_kmp.composeapp.generated.resources.ic_scanner
import me.ibrahim.ebank.kmp.domain.models.BottomTabData
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_LightGrey
import org.jetbrains.compose.resources.painterResource

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