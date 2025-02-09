package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface EBankRoot {

    val backStack: Value<ChildStack<*, MainDestinationChild>>

    sealed class MainDestinationChild {
        data object Splash : MainDestinationChild()
        data object OnBoarding : MainDestinationChild()
    }
}