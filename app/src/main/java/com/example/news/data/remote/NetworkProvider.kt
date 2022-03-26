package com.example.news.data.remote

import com.example.news.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider : NetworkInterface{

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service = retrofit.create(ApiService::class.java)

    override suspend fun getTopHeadLines(country: String, apiKey: String) =
        service.getNews(country, apiKey)

}