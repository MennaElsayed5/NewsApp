package com.example.news.data.local

import androidx.room.TypeConverter
import com.example.news.model.Article
import com.example.news.model.CachedArticle
import com.google.gson.Gson


class Converter {
    @TypeConverter
    fun articleListToGson(value: List<  CachedArticle>) = Gson().toJson(value)

    @TypeConverter
    fun gsonToArticleList(value: String) = Gson().fromJson(value, Array<CachedArticle>::class.java).toList()

}

