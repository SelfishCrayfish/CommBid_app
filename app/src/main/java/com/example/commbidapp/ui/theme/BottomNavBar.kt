package com.example.commbidapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavBar(
    selectedPage: Int,
    onPageSelected: (Int) -> Unit,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    BottomAppBar(
        modifier = Modifier.background(GunmetalColor).height(60.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onPageSelected(0)
                coroutineScope.launch {
                    pagerState.scrollToPage(0)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = if (selectedPage == 0) SunglowColor else CelestialBlueColor
                )
            }

            IconButton(onClick = {
                onPageSelected(1)
                coroutineScope.launch {
                    pagerState.scrollToPage(1)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = if (selectedPage == 1) SunglowColor else CelestialBlueColor
                )
            }

            IconButton(onClick = {
                onPageSelected(2)
                coroutineScope.launch {
                    pagerState.scrollToPage(2)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorites",
                    tint = if (selectedPage == 2) SunglowColor else CelestialBlueColor
                )
            }
        }
    }
}
