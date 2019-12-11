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
    fun updateToken(email: String, token: String)

    @Query("SELECT token FROM " + User.TABLE_NAME + " WHERE email=:user_email")
    fun getToken(user_email: String): String

    @Query("SELECT email FROM " + User.TABLE_NAME + " WHERE email=:user_email")
    fun getUser(user_email: String): String

    @Query("SELECT * FROM "+ User.TABLE_NAME )
    fun getUsers(): LiveData<List<User>>
}