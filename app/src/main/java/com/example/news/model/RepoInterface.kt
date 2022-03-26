package com.example.news.model

interface RepoInterface {
   suspend fun insert(userEntity: UserEntity):Long
    fun getUser (email :String , password:String):Boolean
}