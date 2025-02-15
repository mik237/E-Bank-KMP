package me.ibrahim.ebank.kmp.presentation.ui.money_transfers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ibrahim.ebank.kmp.domain.models.RecentTransfer

@Composable
fun RecentTransfersUI(
    modifier: Modifier = Modifier,
    recentTransfers: List<RecentTransfer>,
    onClick: (RecentTransfer) -> Unit
) {

    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(recentTransfers, key = { _, transfer -> transfer.name }) { index, transfer ->
            RecentTransferItem(
                transfer = transfer,
                currentIndex = index,
                selectedIndex = selectedIndex,
                onClick = {
                    selectedIndex = index
                    onClick(transfer)
                }
            )
        }
    }
}