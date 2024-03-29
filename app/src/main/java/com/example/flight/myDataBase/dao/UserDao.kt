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

    @Query("UPDATE "+ User.TABLE_NAME +" SET token=:token WHERE id =:id")
    fun updateToken(id: String, token: String)

    @Query("SELECT token FROM " + User.TABLE_NAME + " WHERE id=:id")
    fun getToken(id: Int): String

    @Query("SELECT id_remoto FROM " + User.TABLE_NAME + " WHERE id=:id")
    fun getUserId(id: Int): String

    @Query("SELECT id FROM " + User.TABLE_NAME + " WHERE id=:id")
    fun getUserIdLocal(id: Int): Int

    @Query("SELECT * FROM "+ User.TABLE_NAME )
    fun getUsers(): LiveData<List<User>>

}