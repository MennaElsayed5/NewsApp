package com.example.news.ui.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var user:Boolean=true
    fun getUser(email:String,password:String):Boolean{
        viewModelScope.launch {
            userRepository.getUser(email,password)
        }
        return user
    }



}