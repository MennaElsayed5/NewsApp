package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey
    var email : String,
    var name: String,
    var phoneNumber: Int
)