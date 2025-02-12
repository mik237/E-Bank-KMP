package me.ibrahim.ebank.kmp.presentation.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponent
import me.ibrahim.ebank.kmp.presentation.decompose.home.HomeComponentImpl
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

    private val navigation = StackNavigation<MainNavigationConfig>()

    private val _backStack = childStack(
        source = navigation,
        serializer = MainNavigationConfig.serializer(),
        handleBackButton = true,
        initialConfiguration = MainNavigationConfig.Splash,
        childFactory = ::createChild
    )

    override val backStack: Value<ChildStack<MainNavigationConfig, EBankRoot.MainDestinationChild>> = _backStack

    private fun createChild(config: MainNavigationConfig, context: ComponentContext): EBankRoot.MainDestinationChild {
        return when (config) {
            MainNavigationConfig.Splash -> EBankRoot.MainDestinationChild.Splash(component = buildSplashComponent(context))
            MainNavigationConfig.OnBoarding -> EBankRoot.MainDestinationChild.OnBoarding(component = buildOnBoardingComponent(context))
            MainNavigationConfig.Login -> EBankRoot.MainDestinationChild.Login(component = buildLoginComponent(context))
            MainNavigationConfig.Signup -> EBankRoot.MainDestinationChild.Signup(component = buildSignupComponent(context))
            MainNavigationConfig.Home -> EBankRoot.MainDestinationChild.Home(component = buildHomeComponent(context))
        }
    }

    private fun buildSplashComponent(context: ComponentContext): SplashComponent {
        return SplashComponentImpl(splashFinished = { onBoarded ->
            if (onBoarded.not()) navigation.replaceCurrent(MainNavigationConfig.OnBoarding)
            else navigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildOnBoardingComponent(context: ComponentContext): OnBoardingComponent {
        return OnBoardingComponentImpl(skipBoarding = {
            navigation.replaceCurrent(MainNavigationConfig.Login)
        })
    }

    private fun buildLoginComponent(context: ComponentContext): LoginComponent {
        return LoginComponentImpl(
            componentContext = context,
            onSignupClicked = {
                navigation.replaceCurrent(MainNavigationConfig.Signup)
            }
        )
    }

    private fun buildSignupComponent(context: ComponentContext): SignupComponent {
        return SignupComponentImpl(
            componentContext = context,
            onLoginClicked = {
                navigation.replaceCurrent(MainNavigationConfig.Login)
            })
    }

    private fun buildHomeComponent(context: ComponentContext): HomeComponent {
        return HomeComponentImpl()
    }

    @Serializable
    sealed class MainNavigationConfig {
        @Serializable
        data object Splash : MainNavigationConfig()
        data object OnBoarding : MainNavigationConfig()
        data object Login : MainNavigationConfig()
        data object Signup : MainNavigationConfig()
        data object Home : MainNavigationConfig()
    }
}