package com.example.commbidapp.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.commbidapp.Post
import com.example.commbidapp.R
import com.example.commbidapp.RetrofitInstance
import com.example.commbidapp.SomeoneProfileActivity
import com.example.commbidapp.WallViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun WallScreen(viewModel: WallViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),userId : Int?) {
    val posts by viewModel.posts.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(true)
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    val context = LocalContext.current

    // Function to navigate to the user profile screen
    fun navigateToProfile(userId: String) {
        val intent = Intent(context, SomeoneProfileActivity::class.java)
        intent.putExtra("userId", userId.toInt())
        context.startActivity(intent)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchPosts(userId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            WallPosts(posts = posts, navigateToProfile = { username ->
                navigateToProfile(username)
            })
        }
    }




    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            // Show loading indicator while fetching posts
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            // Display error message if something went wrong
            Text(text = errorMessage ?: "", color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            // Display the posts once fetched
            WallPosts(posts = posts, navigateToProfile = ::navigateToProfile)
        }
    }
}

