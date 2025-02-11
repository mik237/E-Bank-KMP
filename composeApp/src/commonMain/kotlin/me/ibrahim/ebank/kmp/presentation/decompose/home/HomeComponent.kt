package me.ibrahim.ebank.kmp.presentation.decompose.home

import com.arkivanov.decompose.value.Value
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageAction
import me.ibrahim.ebank.kmp.presentation.ui.home.HomePageState

interface HomeComponent {
    val state: Value<HomePageState>

    fun onAction(action: HomePageAction)
}