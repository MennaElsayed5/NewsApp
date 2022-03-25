package com.example.news.data.local

import com.example.news.model.User

interface LocalInterface  {
  suspend  fun insert(user: User)
  fun getUser (email :String , password:String):Boolean
}