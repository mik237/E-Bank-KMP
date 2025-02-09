package me.ibrahim.ebank.kmp.presentation.decompose.splash

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.ibrahim.ebank.kmp.presentation.ui.splash.SplashUiState

class SplashComponentImpl(private val splashFinished: (Boolean) -> Unit) : SplashComponent {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val _state = MutableValue<SplashUiState>(SplashUiState.Waiting)
    override val state: Value<SplashUiState> = _state


    init {
        scope.launch {
            delay(2500L)
            _state.value = SplashUiState.Finished
        }
    }


    override fun onSplashFinished() {
        splashFinished(false)
    }
}