package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import me.ibrahim.ebank.kmp.domain.Card
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun CardsPager(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    pagerState: PagerState = rememberPagerState { cards.size }
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val itemSpacing = 16.dp
        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(0)
            ),
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = itemSpacing
        ) { page ->
            CardPage(page = page, pagerState = pagerState, card = cards[page])
            /* Image(
                painter = painterResource(Res.drawable.card_black),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        enabled = true,
                    ) {
                        scope.launch {
                            pagerState.animateScrollToPage(page)
                        }
                    }.graphicsLayer {
                        val pageOffSet = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.75f,
                            stop = 1f,
                            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                        )
                    }
            )*/
        }
    }
}

@Composable
fun CardPage(page: Int, pagerState: PagerState, card: Card) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(12.dp))
            .graphicsLayer {
                val pageOffSet = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                alpha = lerp(
                    start = 0.9f,
                    stop = 1f,
                    fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                )
                scaleY = lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                )
            }
    ) {
        Image(
            painter = painterResource(card.cardImage),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.matchParentSize()
        )
    }
}
