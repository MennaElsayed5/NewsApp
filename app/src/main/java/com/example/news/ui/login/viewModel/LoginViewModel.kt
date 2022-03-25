package com.example.news.ui.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var mutableLiveData=MutableLiveData<Boolean>()
    var userExist:Boolean = true
    fun getUser(email:String,password:String):Boolean{
        viewModelScope.launch (Dispatchers.IO){
           userExist= userRepository.getUser(email,password)
            mutableLiveData.postValue(userExist)
        }
        return userExist
    }



}