package com.example.flight.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flight.myDataBase.model.Flight

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