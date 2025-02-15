package me.ibrahim.ebank.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp


fun maskAccountNumber(input: String): String {
    val result = input.mapIndexed { index, char ->
        when {
            (index > 0 && index < input.length - 4) && char.isDigit() -> "*"
            else -> char
        }
    }.joinToString(separator = "")
    return result
}