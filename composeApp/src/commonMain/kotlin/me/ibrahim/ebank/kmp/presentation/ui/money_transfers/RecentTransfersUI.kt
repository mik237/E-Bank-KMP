package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

@Composable
fun RecentTransfersUI(
    modifier: Modifier = Modifier,
    recentTransfers: List<RecentTransfer>
) {

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recentTransfers, key = { it.name }) { transfer ->
            RecentTransferItem(transfer = transfer)
        }
    }
}