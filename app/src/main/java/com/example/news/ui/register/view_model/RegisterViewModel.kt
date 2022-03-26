package com.example.news.ui.register.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.User
import com.example.news.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    var mutableLiveData = MutableLiveData<Boolean>()
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

    private var exist: Boolean = true
    fun getExistUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            exist = userRepository.getUser(email, password)
            mutableLiveData.postValue(exist)

        }
    }

}