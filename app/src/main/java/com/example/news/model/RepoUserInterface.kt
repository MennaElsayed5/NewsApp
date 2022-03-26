package com.example.news.model

interface RepoUserInterface {
   suspend fun insert(userEntity: UserEntity):Long
    fun getUser (email :String , password:String):Boolean
}