package me.ibrahim.ebank.kmp.domain.models

import androidx.compose.ui.graphics.Color
import me.ibrahim.ebank.kmp.domain.constants.QuickActionType
import org.jetbrains.compose.resources.DrawableResource

data class QuickAction(
    val title: String,
    val icon: DrawableResource,
    val iconColor: Color,
    val type: QuickActionType
)
