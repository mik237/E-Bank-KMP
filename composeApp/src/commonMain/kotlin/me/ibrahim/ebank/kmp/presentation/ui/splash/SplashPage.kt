package me.ibrahim.ebank.kmp.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.bg_gradiant
import me.ibrahim.ebank.kmp.core.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.presentation.decompose.splash.SplashComponent
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashPage(
    component: SplashComponent
) {
    val state by component.state.subscribeAsState()

    LaunchedEffect(state) {
        if (state is SplashUiState.Finished) {
            component.onSplashFinished()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.ThemeColor_DarkGrey)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .align(Alignment.Center),
            painter = painterResource(Res.drawable.bg_gradiant),
            contentDescription = null
        )
    }
}