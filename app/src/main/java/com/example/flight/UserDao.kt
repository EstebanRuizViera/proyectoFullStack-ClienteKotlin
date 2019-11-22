package com.example.pruebaslogin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(vararg user: User)

    @Delete
    fun delete(vararg user: User)

    @Query("UPDATE "+ User.TABLE_NAME +" SET token=:token WHERE email = :email")
    fun updateToken(token:String,email:String)

    @Query("SELECT name FROM " + User.TABLE_NAME + " WHERE email=:user_email")
    fun getUser(user_email:String):String
}