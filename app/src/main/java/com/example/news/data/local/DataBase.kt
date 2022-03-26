package com.example.news.data.local

import android.app.Application
import androidx.room.Database
import com.example.news.model.UserEntity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDatabase(application: Application): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext, DataBase::class.java, "News"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}