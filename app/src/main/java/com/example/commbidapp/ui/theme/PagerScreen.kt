package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PagerScreen() {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Scaffold { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) {
                0 -> WallScreen()
                1 -> UserProfileScreen()
                2 -> FavoritesScreen()
            }
        }
    }
}
