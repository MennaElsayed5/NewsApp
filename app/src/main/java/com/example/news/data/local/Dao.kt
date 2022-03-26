package com.example.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.news.model.User

@Dao
interface Dao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT EXISTS (SELECT * FROM users WHERE email =:email AND password =:password)")
    fun getUser (email :String , password:String):Boolean
}