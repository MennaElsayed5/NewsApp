package com.example.news.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.news.data.remote.NetworkInterface
import com.example.news.model.network.Resource
import com.example.news.util.NEWS_API_KEY
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeViewModel(private val networkInterface: NetworkInterface) : ViewModel() {

    fun getNews(src: String = "us", apiKey: String = NEWS_API_KEY) = liveData(Dispatchers.IO) {
        val data = networkInterface.getTopHeadLines(src, apiKey)
        emit(Resource.Loading(isLoading = true, _data = null))
        try {
            emit(Resource.Success(_data = data))
        } catch (exception: Exception) {
            emit(Resource.Error(_msg = exception.message.toString()))
        }
    }
}