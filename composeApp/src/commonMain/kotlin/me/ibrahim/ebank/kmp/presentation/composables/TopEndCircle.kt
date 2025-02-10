package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun TopEndCircle(color: Color) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val width = size.width
        val height = size.height

        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f, // Sweep the arc 360 degrees
            useCenter = false,
            topLeft = Offset(width / 2.2f, -height / 2.2f), // Position the arc at the top left corner
            size = Size(width = width, height = height), // Adjust the size of the arc
            alpha = 0.1f
        )
    }
}