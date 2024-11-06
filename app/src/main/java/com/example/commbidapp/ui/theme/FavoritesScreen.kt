package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.commbidapp.R

data class FavoritePost(
    val postImage: Int // Identyfikator zasobu obrazu dla ulubionego posta
)

@Composable
fun FavoritesScreen() {
    // Przykładowa lista ulubionych postów z obrazami
    val favoritePosts = listOf(
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2),
        FavoritePost(postImage = R.drawable.drawing_template_3),
        FavoritePost(postImage = R.drawable.drawing_template_1),
        FavoritePost(postImage = R.drawable.drawing_template_2)
    )

    // Siatka dla ulubionych postów: 3 kolumny w rzędzie
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(4.dp)
    ) {
        items(favoritePosts) { favoritePost ->
            // Pojedynczy obraz w siatce ulubionych postów
            Image(
                painter = painterResource(id = favoritePost.postImage),
                contentDescription = "Favorite Post Image",
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp),
                alignment = Alignment.Center
            )
        }
    }
}
