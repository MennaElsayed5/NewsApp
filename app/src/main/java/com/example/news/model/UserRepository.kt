package com.example.news.model

import android.app.Application
import com.example.news.data.local.Dao
import com.example.news.data.local.DataBase


class UserRepository private constructor(var dao: Dao) : RepoInterface {
    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getRepoInstance(application: Application): UserRepository {
            return INSTANCE ?: synchronized(this) {
                UserRepository(
                    DataBase.getDatabase(application).dao(),
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    override suspend fun insert(userEntity: UserEntity):Long {
     return   dao.insert(userEntity)
    }

    override fun getUser(email: String, password: String): Boolean{
        return dao.getUser(email, password)
    }
}