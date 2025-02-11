package me.ibrahim.ebank.kmp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.available_balance
import e_bank_kmp.composeapp.generated.resources.card_holder
import e_bank_kmp.composeapp.generated.resources.ic_master
import e_bank_kmp.composeapp.generated.resources.ic_sim
import e_bank_kmp.composeapp.generated.resources.ic_visa
import e_bank_kmp.composeapp.generated.resources.valid_from
import e_bank_kmp.composeapp.generated.resources.valid_thru
import me.ibrahim.ebank.kmp.domain.Card
import me.ibrahim.ebank.kmp.utils.CardType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(card.cardImage),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.matchParentSize()
        )
        Row(
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.Center)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(Res.string.available_balance),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "$${card.balance}",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = card.cardNumber,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = stringResource(Res.string.valid_from, card.validFrom),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        text = stringResource(Res.string.valid_thru, card.validThru),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(Res.string.card_holder),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = card.cardHolderName,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_sim),
                    contentDescription = null,
                    modifier = Modifier.height(30.dp)
                )

                Image(
                    painter = painterResource(
                        when (card.cardType) {
                            CardType.MASTER -> Res.drawable.ic_master
                            CardType.VISA -> Res.drawable.ic_visa
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.width(50.dp)
                )
            }
        }
    }
}
