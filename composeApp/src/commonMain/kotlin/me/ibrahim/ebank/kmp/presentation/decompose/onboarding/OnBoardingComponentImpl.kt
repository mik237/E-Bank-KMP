package me.ibrahim.ebank.kmp.presentation.decompose.onboarding

class OnBoardingComponentImpl(
    private val skipBoarding: () -> Unit
) : OnBoardingComponent {

    override fun onSkipBoarding() = skipBoarding()

}