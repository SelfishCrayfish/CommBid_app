package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.commbidapp.R

@Composable
fun WallScreen() {
    val samplePosts = listOf(
        Post(
            profilePicture = R.drawable.profile_pic,
            nickname = "Jan Kowalski",
            username = "janek123",
            postImage = R.drawable.drawing_template_1,
            description = "Przykładowy opis posta użytkownika Janek. To jest bardzo ciekawy post z dużą ilością informacji."
        ),
        Post(
            profilePicture = R.drawable.profile_pic,
            nickname = "Anna Nowak",
            username = "aniusia",
            postImage = R.drawable.drawing_template_2,
            description = "To kolejny post, tym razem dodany przez Annę. Ma interesujący opis, ale jest krótki."
        ),
        Post(
            profilePicture = R.drawable.profile_pic,
            nickname = "Piotr Zawada",
            username = "piotrek",
            postImage = R.drawable.drawing_template_3,
            description = "Opis posta dodanego przez Piotra. Post jest pełen entuzjazmu i radości."
        ),
        Post(
            profilePicture = R.drawable.profile_pic,
            nickname = "Zbuku Zawadiaka",
            username = "magik",
            postImage = R.drawable.drawing_template_3,
            description = "Opis posta dodanego przez Zbuka. Post jest pełen marazmu i chandry."
        )

    )

    Column(modifier = Modifier.fillMaxSize()) {
        WallPosts(posts = samplePosts)
    }
}