package com.example.news.data.remote

import com.example.news.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(ApiService::class.java)

    fun getTopHeadLines(country: String, apiKey: String) =
        service.getNews(country, apiKey)

}