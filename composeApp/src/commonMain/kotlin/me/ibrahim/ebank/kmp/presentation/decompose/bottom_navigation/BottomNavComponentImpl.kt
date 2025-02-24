package me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
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
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.BottomNavComponent.BottomNavChild
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRootImpl

class BottomNavComponentImpl(componentContext: ComponentContext) :
    BottomNavComponent,
    ComponentContext by componentContext {
    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    override val configs: List<BottomNavConfig> = listOf(
        BottomNavConfig.HomeRoot,
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

    private fun createChildPageFactor(config: BottomNavConfig, context: ComponentContext): BottomNavChild {
        return when (config) {
            BottomNavConfig.Analytics -> BottomNavChild.Analytics
            BottomNavConfig.HomeRoot -> BottomNavChild.HomeRoot(component = buildHomeRootComponent(context))
            BottomNavConfig.Locations -> BottomNavChild.Locations
            BottomNavConfig.Profile -> BottomNavChild.Profile
            BottomNavConfig.Scanner -> BottomNavChild.Scanner
        }
    }

    private fun buildHomeRootComponent(context: ComponentContext): EBankRoot {
        return EBankRootImpl(context)
    }


    override val pages: Value<ChildPages<*, BottomNavChild>>
        get() = _pages


    override fun onNewPageSelected(index: Int) {
        mainDispatcher.launch {
            navigation.select(index)
            println("CustomPager: index $index")
        }
    }

    @Serializable
    sealed interface BottomNavConfig {
        @Serializable
        data object HomeRoot : BottomNavConfig

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