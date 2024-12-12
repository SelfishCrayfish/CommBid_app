package com.example.commbidapp.ui.theme

import androidx.compose.runtime.Composable
import com.example.commbidapp.WallViewModel

@Composable
fun FavoritesScreen(viewModel: WallViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    WallScreen(viewModel = viewModel)
}
