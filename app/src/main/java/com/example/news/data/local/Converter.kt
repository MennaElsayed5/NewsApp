package com.example.news.data.local

import androidx.room.TypeConverter
import com.example.news.model.Article
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun articleListToGson(value: List<Article>) = Gson().toJson(value)

    @TypeConverter
    fun gsonToArticleList(value: String) = Gson().fromJson(value, Array<Article>::class.java).toList()
}