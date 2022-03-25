package com.example.news.ui.register.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.User
import com.example.news.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

}