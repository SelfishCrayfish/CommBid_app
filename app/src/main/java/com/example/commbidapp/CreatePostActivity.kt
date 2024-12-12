package com.example.commbidapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.commbidapp.ui.theme.CreatePostScreen
import com.example.commbidapp.ui.theme.PagerScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CreatePostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current // Proper way to get the context in Compose
            CreatePostScreen(
                onPostAdded = { post: Post ->
                    val postService = RetrofitInstance.postService

                    postService.createPost(post).enqueue(object : Callback<Post> {
                        override fun onResponse(call: Call<Post>, response: Response<Post>) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    Toast.makeText(
                                        context,
                                        R.string.post_added_successfully,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Navigate to the next screen
                                    (context as? ComponentActivity)?.setContent {
                                        PagerScreen(pageNumber = 1)
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Failed to add post: ${response.message()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Post>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                },
                onCancel = {
                    // Navigate to the next screen on cancel
                    setContent {
                        PagerScreen(pageNumber = 1)
                    }
                }
            )
        }
    }
}
