package com.example.news.data.remote

import com.example.news.model.NewsApi

interface NetworkInterface {
    suspend fun getTopHeadLines(country: String, apiKey: String) : NewsApi
}