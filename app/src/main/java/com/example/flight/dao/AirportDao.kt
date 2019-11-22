package com.example.flight.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flight.model.Airport

@Dao
interface AirportDao {
    @Insert
    fun insert(airport: Airport)

    @Update
    fun update(vararg airport: Airport)

    @Delete
    fun delete(vararg airport: Airport)

    @Query("UPDATE "+ Airport.TABLE_NAME +" SET token=:token WHERE email = :email")
    fun updateToken(email:String,token:String)

    @Query("SELECT token FROM " + Airport.TABLE_NAME + " WHERE email=:user_email")
    fun getAirport(user_email:String):String

    @Query("SELECT * FROM "+ Airport.TABLE_NAME )
    fun getAirports(): LiveData<List<Airport>>
}