package com.kenruizinoue.bottombarcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.kenruizinoue.bottombarcomposable.composable.BottomBar
import com.kenruizinoue.bottombarcomposable.data.BottomBarItemData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Managing Selected Item State
            val selectedBottomBarItem =
                remember { mutableStateOf(BottomBarItemData(1, "Home", R.drawable.ic_home)) }
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Selected: ${selectedBottomBarItem.value.title}",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp
                )
                BottomBar(
                    selectedBottomBarItem = selectedBottomBarItem,
                    bottomBarItems = listOf(
                        BottomBarItemData(1, "Home", R.drawable.ic_home),
                        BottomBarItemData(2, "Search", R.drawable.ic_search),
                        BottomBarItemData(3, "Library", R.drawable.ic_music_library),
                        BottomBarItemData(4, "Settings", R.drawable.ic_settings)
                    ),
                    primaryColor = Color(0xFF3F51B5),
                    secondaryColor = Color(0xFFC4CBF5),
                    // 2. Bottom Bar Layout and Alignment
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                )
            }
        }
    }
}