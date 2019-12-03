package com.example.pruebaslogin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flight.myDataBase.dao.AirportDao
import com.example.flight.myDataBase.dao.FlightDao
import com.example.flight.myDataBase.model.Airport
import com.example.flight.myDataBase.model.Flight

@Database(entities = [User::class, Flight::class,Airport::class], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun flightDao(): FlightDao
    abstract fun airportDao(): AirportDao


    companion object {
        private const val DATABASE_NAME = "flight_ddbb"
        @Volatile
        private var INSTANCE: FlightDatabase? = null

        fun getInstance(context: Context): FlightDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    FlightDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}