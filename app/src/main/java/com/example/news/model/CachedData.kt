package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "News")
data class CachedData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val cachedData: List<Article>
)
























