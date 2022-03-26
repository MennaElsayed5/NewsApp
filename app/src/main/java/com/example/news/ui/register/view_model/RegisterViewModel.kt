package com.example.news.ui.register.view_model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.UserEntity
import com.example.news.model.UserRepository
import com.example.news.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val mutableLiveData = MutableLiveData<RegisterRuselt>()
    val registerResult: LiveData<RegisterRuselt>
        get() = mutableLiveData


    fun register(users: Users) {
        checkData(users)

    }

    fun successRegister(users: Users) {
        viewModelScope.launch {
            val entity =
                UserEntity(users.email!!, users.name!!, users.phoneNumber!!, users.password!!)
            withContext(Dispatchers.IO)
            {
                try {
                    val result = userRepository.insert(entity)
                    if (result > 0) {
                        mutableLiveData.postValue(RegisterRuselt.CompleteSuccess)
                    }

                } catch (ex: Exception) {
                    mutableLiveData.postValue(RegisterRuselt.DuplicateData)

                }
            }

        }
    }

    private fun checkData(users: Users) {

        val isValidEmail =
            Patterns.EMAIL_ADDRESS.matcher(users.email).matches() && !users.email.isNullOrEmpty()
        val isValidPhone = Patterns.PHONE.matcher(users.phoneNumber)
            .matches() && !users.phoneNumber.isNullOrEmpty()
        val isValidName = !users.name.isNullOrEmpty()
        val isValidPassword = !users.password.isNullOrEmpty()
        if (!isValidEmail) {
            mutableLiveData.value = RegisterRuselt.InvalidData(ErrorData.EMAIL)
        } else if (!isValidPhone) {
            mutableLiveData.value = RegisterRuselt.InvalidData(ErrorData.PHONE)
        } else if (!isValidName) {
            mutableLiveData.value = RegisterRuselt.InvalidData(ErrorData.NAME)
        } else if (!isValidPassword) {
            mutableLiveData.value = RegisterRuselt.InvalidData(ErrorData.PASSWORD)
        } else {
            successRegister(users)
        }
    }
}

sealed class RegisterRuselt {
    object CompleteSuccess : RegisterRuselt()
    data class InvalidData(val errorData: ErrorData) : RegisterRuselt()
    object DuplicateData : RegisterRuselt()

}

enum class ErrorData {
    EMAIL,
    NAME,
    PHONE,
    PASSWORD
}