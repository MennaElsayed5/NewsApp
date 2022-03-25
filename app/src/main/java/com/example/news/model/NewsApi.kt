package com.example.news.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "News")
data class NewsApi(
    val id: String,
    val status:String,
    val totalResults:Int,
    val articles: List<Article>
)

data class Article (
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source (
    val id: String,
    val name: String
)
