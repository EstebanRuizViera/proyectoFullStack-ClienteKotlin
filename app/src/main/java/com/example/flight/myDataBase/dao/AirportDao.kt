package com.example.flight.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flight.myDataBase.model.Airport

@Dao
interface AirportDao {
    @Insert
    fun insert(airport: Airport)

    @Update
    fun update(vararg airport: Airport)

    @Delete
    fun delete(vararg airport: Airport)

    @Query("SELECT * FROM "+ Airport.TABLE_NAME )
    fun getAirports(): LiveData<List<Airport>>
}