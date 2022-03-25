package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "News")
data class CachedData(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val article: List<CachedArticle>
)
data class CachedArticle (
    var sourceName: String?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)


























