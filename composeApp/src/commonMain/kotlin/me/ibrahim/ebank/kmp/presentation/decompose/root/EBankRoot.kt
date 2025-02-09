package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponent

interface EBankRoot {

    val backStack: Value<ChildStack<*, MainDestinationChild>>

    sealed class MainDestinationChild {
        data class Splash(val component: SplashComponent) : MainDestinationChild()
        data class OnBoarding(val component: OnBoardingComponent) : MainDestinationChild()
    }
}