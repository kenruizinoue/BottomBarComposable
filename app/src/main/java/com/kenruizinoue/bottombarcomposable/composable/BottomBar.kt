package com.kenruizinoue.bottombarcomposable.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kenruizinoue.bottombarcomposable.R
import com.kenruizinoue.bottombarcomposable.data.BottomBarItemData

/**
 * Composable function to create a customizable bottom navigation bar with multiple items.
 *
 * @param bottomBarItems List of data items for each bottom bar item. Default is an empty list.
 * @param selectedBottomBarItem Mutable state to track the currently selected item. Default is empty BottomBarItemData.
 * @param barHeight Height of the bottom bar. Default value is 64.dp.
 * @param primaryColor Primary color used for the bar and selected items. Default is black (0xFF000000).
 * @param secondaryColor Secondary color used for non-selected items. Default is white (0xFFFFFFFF).
 * @param iconSize Size of the icons in the bar. Default value is 24.dp.
 * @param selectionCircleSize Size of the selection circle around selected items. Default is 36.dp.
 * @param modifier Modifier applied to the composable for customization. Default is an empty Modifier.
 */
@Composable
fun BottomBar(
    bottomBarItems: List<BottomBarItemData> = listOf(),
    // 1. Centralized Selection State
    selectedBottomBarItem: MutableState<BottomBarItemData> = mutableStateOf(BottomBarItemData()),
    barHeight: Dp = 64.dp,
    primaryColor: Color = Color(0xFF000000),
    secondaryColor: Color = Color(0xFFFFFFFF),
    iconSize: Dp = 24.dp,
    selectionCircleSize: Dp = 36.dp,
    modifier: Modifier = Modifier
) {
    // 2. Item Width Calculation
    val itemWidth = LocalConfiguration.current.screenWidthDp.dp / bottomBarItems.size
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(barHeight)
            .background(primaryColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(bottomBarItems) { item ->
            BottomBarItemUi(
                itemData = item,
                modifier = Modifier.width(itemWidth),
                // 3. Ensured Single Selection Logic
                selected = selectedBottomBarItem.value.id == item.id,
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                iconSize = iconSize,
                selectionCircleSize = selectionCircleSize
            ) {
                selectedBottomBarItem.value = item
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(
        bottomBarItems = listOf(
            BottomBarItemData(1, "Home", R.drawable.ic_home),
            BottomBarItemData(2, "Search", R.drawable.ic_search),
            BottomBarItemData(3, "Library", R.drawable.ic_music_library)
        )
    )
}