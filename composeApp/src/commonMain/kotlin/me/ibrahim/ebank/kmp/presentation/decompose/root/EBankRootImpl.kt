@file:OptIn(DelicateDecomposeApi::class)

package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import me.ibrahim.ebank.kmp.presentation.decompose.dashboard.DashboardComponent
import me.ibrahim.ebank.kmp.presentation.decompose.dashboard.DashboardComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponent
import me.ibrahim.ebank.kmp.presentation.decompose.onboarding.OnBoardingComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponent
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponent
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponentImpl

class EBankRootImpl(
    componentContext: ComponentContext
) : EBankRoot, ComponentContext by componentContext {

    private val mainNavigation = StackNavigation<MainNavigationConfig>()

    private val _backStack = childStack(
        source = mainNavigation,
        serializer = MainNavigationConfig.serializer(),
        handleBackButton = true,
        initialConfiguration = MainNavigationConfig.Splash,
        childFactory = ::createChild
    )

    override val backStack: Value<ChildStack<MainNavigationConfig, EBankRoot.MainDestinationChild>> = _backStack

    private fun createChild(config: MainNavigationConfig, context: ComponentContext): EBankRoot.MainDestinationChild {
        return when (config) {
            MainNavigationConfig.Splash -> EBankRoot.MainDestinationChild.Splash(component = buildSplashComponent())
            MainNavigationConfig.OnBoarding -> EBankRoot.MainDestinationChild.OnBoarding(component = buildOnBoardingComponent())
            MainNavigationConfig.Login -> EBankRoot.MainDestinationChild.Login(component = buildLoginComponent(context))
            MainNavigationConfig.Signup -> EBankRoot.MainDestinationChild.Signup(component = buildSignupComponent(context))
            MainNavigationConfig.Dashboard -> EBankRoot.MainDestinationChild.Dashboard(component = buildDashboardComponent(context))
        }
    }

    private fun buildDashboardComponent(context: ComponentContext): DashboardComponent {
        return DashboardComponentImpl(componentContext = context)
    }


    private fun buildSplashComponent(): SplashComponent {
        return SplashComponentImpl(splashFinished = { onBoarded ->
            if (onBoarded.not()) mainNavigation.replaceCurrent(MainNavigationConfig.OnBoarding)
            else mainNavigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildOnBoardingComponent(): OnBoardingComponent {
        return OnBoardingComponentImpl(skipBoarding = {
            mainNavigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildLoginComponent(context: ComponentContext): LoginComponent {
        return LoginComponentImpl(
            componentContext = context,
            onSignupClicked = {
                mainNavigation.replaceCurrent(MainNavigationConfig.Signup)
            },
            onLoginClicked = { username, password ->
                mainNavigation.replaceCurrent(MainNavigationConfig.Dashboard)
            }
        )
    }

    private fun buildSignupComponent(context: ComponentContext): SignupComponent {
        return SignupComponentImpl(
            componentContext = context,
            onLoginClicked = {
                mainNavigation.replaceCurrent(MainNavigationConfig.Login)
            })
    }


    @Serializable
    sealed class MainNavigationConfig {
        @Serializable
        data object Splash : MainNavigationConfig()
        data object OnBoarding : MainNavigationConfig()
        data object Login : MainNavigationConfig()
        data object Signup : MainNavigationConfig()
        data object Dashboard : MainNavigationConfig()
    }
}