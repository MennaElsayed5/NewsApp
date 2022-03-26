package com.example.news.model

interface RepoInterface {
    suspend fun insert(user: User)
    fun getUser(email: String, password: String): Boolean
}