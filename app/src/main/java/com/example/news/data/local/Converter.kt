package com.example.news.data.local

import androidx.room.TypeConverter
import com.example.news.model.NewsApi
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun newsApiToString(value: NewsApi) = Gson().toJson(value)

    @TypeConverter
    fun stringToNewsApi(value: String) = Gson().fromJson(value, NewsApi::class.java).toString()
}