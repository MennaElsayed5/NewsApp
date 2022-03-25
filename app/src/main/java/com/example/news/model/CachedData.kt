package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "News")
data class CachedData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val article: List<Article>
)
data class cachedArticle (
    val sourceName: String?,
    val author: String? ,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)


























