package com.kenruizinoue.bottombarcomposable.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kenruizinoue.bottombarcomposable.R
import com.kenruizinoue.bottombarcomposable.data.BottomBarItemData

/**
 * Composable function to create a bottom bar item for navigation.
 *
 * @param itemData Data for the bottom bar item, includes icon ID and title. Default is an empty data set.
 * @param selected Indicates if the item is currently selected. Default is false.
 * @param primaryColor The primary color used for the selected state. Default is black (0xFF000000).
 * @param secondaryColor The secondary color used for the non-selected state. Default is white (0xFFFFFFFF).
 * @param iconSize The size of the icon. Default value is 24.dp.
 * @param selectionCircleSize The size of the selection circle shown when selected. Default is 36.dp.
 * @param modifier Modifier applied to the composable for customization. Default is an empty Modifier.
 * @param onClick Lambda function to handle click events. Receives the item data as a parameter.
 */
@Composable
fun BottomBarItemUi(
    itemData: BottomBarItemData = BottomBarItemData(),
    selected: Boolean = false,
    primaryColor: Color = Color(0xFF000000),
    secondaryColor: Color = Color(0xFFFFFFFF),
    iconSize: Dp = 24.dp,
    selectionCircleSize: Dp = 36.dp,
    modifier: Modifier = Modifier,
    // 1. Action Handler as a Trailing Lambda
    onClick: (BottomBarItemData) -> Unit = {},
) {
    // 2. Selection Indicator UX
    val color = if (selected) primaryColor else secondaryColor
    val alphaValue by animateFloatAsState(targetValue = if (selected) 1f else 0f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.then(Modifier.clickable {
            onClick(itemData)
        })
    ) {
        Box(
            modifier = Modifier
                .size(selectionCircleSize)
                .background(secondaryColor.copy(alpha = alphaValue), CircleShape)
        )
        Icon(
            painter = painterResource(id = itemData.iconId),
            contentDescription = itemData.title,
            tint = color,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview
@Composable
fun BottomBarItemUiPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Text("Selected", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        BottomBarItemUi(
            itemData = BottomBarItemData(1, "Home", R.drawable.ic_home),
            selected = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Not Selected", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        BottomBarItemUi(
            itemData = BottomBarItemData(1, "Home", R.drawable.ic_home),
            selected = false
        )
    }
}