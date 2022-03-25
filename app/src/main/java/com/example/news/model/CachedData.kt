package com.example.news.model

import androidx.room.PrimaryKey

data class CachedData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val cachedData: NewsApi
)
