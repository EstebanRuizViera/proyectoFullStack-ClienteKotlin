package com.example.flight.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flight.model.Flight

@Dao
interface FlightDao {
    @Insert
    fun insert(flight: Flight)

    @Update
    fun update(vararg flight: Flight)

    @Delete
    fun delete(vararg flight: Flight)

    @Query("UPDATE "+ Flight.TABLE_NAME +" SET token=:token WHERE email = :email")
    fun updateToken(email:String,token:String)

    @Query("SELECT token FROM " + Flight.TABLE_NAME + " WHERE email=:user_email")
    fun getFlight(user_email:String):String

    @Query("SELECT * FROM "+ Flight.TABLE_NAME )
    fun getFlights(): LiveData<List<Flight>>
}