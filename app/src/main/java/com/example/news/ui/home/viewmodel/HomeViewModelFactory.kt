package com.example.news.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.data.remote.NetworkInterface

class HomeViewModelFactory(private val networkInterface: NetworkInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(networkInterface) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}