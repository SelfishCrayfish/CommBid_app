package com.example.commbidapp
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {
    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        RetrofitInstance.postService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    _posts.value = response.body() ?: emptyList()
                } else {
                    // Handle API errors here
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Handle network errors here
            }
        })
    }
}
