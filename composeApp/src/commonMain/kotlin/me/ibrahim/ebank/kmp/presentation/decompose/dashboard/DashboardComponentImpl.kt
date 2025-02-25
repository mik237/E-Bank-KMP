package me.ibrahim.ebank.kmp.presentation.decompose.dashboard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab.HomeTabComponent
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.home_tab.HomeTabComponentImpl

class DashboardComponentImpl(componentContext: ComponentContext) : DashboardComponent, ComponentContext by componentContext {

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    override val configs: List<BottomNavConfig> = listOf(
        BottomNavConfig.Home,
        BottomNavConfig.Locations,
        BottomNavConfig.Scanner,
        BottomNavConfig.Analytics,
        BottomNavConfig.Profile,
    )

    private val navigation = PagesNavigation<BottomNavConfig>()

    private val _pages = childPages(
        source = navigation,
        serializer = BottomNavConfig.serializer(),
        initialPages = {
            Pages(items = configs, selectedIndex = 1)
        },
        childFactory = ::createChildPageFactor
    )

    private fun createChildPageFactor(config: BottomNavConfig, context: ComponentContext): DashboardComponent.BottomNavChild {
        return when (config) {
            BottomNavConfig.Analytics -> DashboardComponent.BottomNavChild.Analytics
            BottomNavConfig.Home -> DashboardComponent.BottomNavChild.Home(component = buildHomeTabComponent(context))
            BottomNavConfig.Locations -> DashboardComponent.BottomNavChild.Locations
            BottomNavConfig.Profile -> DashboardComponent.BottomNavChild.Profile
            BottomNavConfig.Scanner -> DashboardComponent.BottomNavChild.Scanner
        }
    }

    private fun buildHomeTabComponent(context: ComponentContext): HomeTabComponent {
        return HomeTabComponentImpl(componentContext = context)
    }


    override val pages: Value<ChildPages<*, DashboardComponent.BottomNavChild>>
        get() = _pages


    override fun onNewPageSelected(index: Int) {
        mainDispatcher.launch {
            navigation.select(index)
        }
    }

    @Serializable
    sealed interface BottomNavConfig {
        @Serializable
        data object Home : BottomNavConfig

        @Serializable
        data object Locations : BottomNavConfig

        @Serializable
        data object Scanner : BottomNavConfig

        @Serializable
        data object Analytics : BottomNavConfig

        @Serializable
        data object Profile : BottomNavConfig
    }
}