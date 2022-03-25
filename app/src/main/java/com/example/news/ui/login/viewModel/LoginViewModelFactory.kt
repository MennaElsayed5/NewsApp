package com.example.news.ui.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.model.UserRepository
import com.example.news.ui.register.view_model.RegisterViewModel

class LoginViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }
}