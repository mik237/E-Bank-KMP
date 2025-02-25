package me.ibrahim.ebank.kmp.presentation.decompose.dashboard

import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab.HomeTabComponent

interface DashboardComponent {

    val pages: Value<ChildPages<*, BottomNavChild>>

    val configs: List<DashboardComponentImpl.BottomNavConfig>

    fun onNewPageSelected(index: Int)

    sealed interface BottomNavChild {
        data class Home(val component: HomeTabComponent) : BottomNavChild
        data object Locations : BottomNavChild
        data object Scanner : BottomNavChild
        data object Analytics : BottomNavChild
        data object Profile : BottomNavChild
    }

}