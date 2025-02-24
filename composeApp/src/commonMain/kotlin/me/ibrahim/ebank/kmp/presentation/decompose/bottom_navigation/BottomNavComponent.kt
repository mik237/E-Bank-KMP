package me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation

import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRoot

interface BottomNavComponent {

    val pages: Value<ChildPages<*, BottomNavChild>>

    val configs: List<BottomNavComponentImpl.BottomNavConfig>

    fun onNewPageSelected(index: Int)

    sealed interface BottomNavChild {
        data class HomeRoot(val component: EBankRoot) : BottomNavChild
        data object Locations : BottomNavChild
        data object Scanner : BottomNavChild
        data object Analytics : BottomNavChild
        data object Profile : BottomNavChild
    }
}