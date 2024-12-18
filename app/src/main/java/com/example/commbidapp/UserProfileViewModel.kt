package com.example.commbidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchUserById(userId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedUser = RetrofitInstance.userService.getUserById(userId)
                _user.value = fetchedUser
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
