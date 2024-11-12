package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun PagerScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    var selectedPage by remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        selectedPage = pagerState.currentPage
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                selectedPage = selectedPage,
                onPageSelected = { page ->
                    selectedPage = page
                },
                coroutineScope = coroutineScope,
                pagerState = pagerState
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) {
                0 -> MainScreenWithNavbar { WallScreen() }
                1 -> MainScreenWithNavbar { UserProfileScreen() }
                2 -> MainScreenWithNavbar { FavoritesScreen() }
            }
        }
    }
}
