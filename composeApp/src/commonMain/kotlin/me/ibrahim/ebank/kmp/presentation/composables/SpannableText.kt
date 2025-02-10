package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle

@Composable
fun SpannableText(
    planeText: String = "",
    annotatedText: String = "",
    planeTextColor: Color = Color.Transparent,
    annotatedTextColor: Color = Color.Transparent,
    onAnnotationClicked: (LinkAnnotation) -> Unit
) {
    val text = buildAnnotatedString {

        if (planeText.isNotEmpty()) {
            withStyle(style = SpanStyle(color = planeTextColor)) {
                append(planeText)
            }
        }

        withLink(
            link = LinkAnnotation.Clickable(
                tag = "TAG",
                styles = null,
                linkInteractionListener = onAnnotationClicked
            )
        ) {
            withStyle(style = SpanStyle(color = annotatedTextColor)) {
                append(annotatedText)
            }
        }
    }

    Text(
        text = text,
    )
}