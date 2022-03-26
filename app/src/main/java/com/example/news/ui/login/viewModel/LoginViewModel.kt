package com.example.news.ui.login.viewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val mutableLiveData = MutableLiveData<LoginResult>()
    val liveData: LiveData<LoginResult>
        get() = mutableLiveData


    fun login(email:String,password:String) {
        checkData(email,password)
    }

    fun successLogin(email:String,password:String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    val result = userRepository.getUser(email,password)
                    if (result) {
                        mutableLiveData.postValue(LoginResult.CompleteSuccess)
                    }

                } catch (ex: Exception) {
                    mutableLiveData.postValue(LoginResult.FailData)

                }
            }

        }
    }

    private fun checkData(email:String,password: String) {

        val isValidEmail =
            Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isNullOrEmpty()
        val isValidPassword = !password.isNullOrEmpty()
        if (!isValidEmail) {
            mutableLiveData.value = LoginResult.InvalidData(ErrorData.EMAIL)
        } else if (!isValidPassword) {
            mutableLiveData.value = LoginResult.InvalidData(ErrorData.PASSWORD)
        } else {
            successLogin(email,password)
        }
    }

}

sealed class LoginResult {
    object CompleteSuccess : LoginResult()
    data class InvalidData(val errorData: ErrorData) : LoginResult()
    object FailData : LoginResult()

}

enum class ErrorData {
    EMAIL,
    PASSWORD
}


