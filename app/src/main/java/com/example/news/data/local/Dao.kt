package com.example.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.model.UserEntity

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(userEntity: UserEntity):Long

    @Query("SELECT EXISTS (SELECT * FROM users WHERE email =:email AND password =:password)")
    fun getUser (email :String , password:String):Boolean
}