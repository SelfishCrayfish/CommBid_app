package com.example.commbidapp.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.commbidapp.R
import com.example.commbidapp.SomeoneProfileActivity

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

    val context = LocalContext.current

    // Funkcja nawigacji do profilu użytkownika
    fun navigateToProfile(username: String) {
        val intent = Intent(context, SomeoneProfileActivity::class.java)
        intent.putExtra("USERNAME", username)
        context.startActivity(intent)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        WallPosts(posts = samplePosts, navigateToProfile = ::navigateToProfile)
    }
}