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

    @Query("SELECT * FROM "+ Flight.TABLE_NAME )
    fun getFlights(): LiveData<List<Flight>>
}