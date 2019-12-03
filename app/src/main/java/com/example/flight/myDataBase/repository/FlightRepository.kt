package com.example.flight.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flight.myDataBase.dao.FlightDao
import com.example.flight.myDataBase.model.Flight
import com.example.pruebaslogin.FlightDatabase

class FlightRepository (application: Application) {
    private val flightDao: FlightDao? = FlightDatabase.getInstance(application)?.flightDao()

    fun insert(flight: Flight) {
        if (flightDao != null) InsertAsyncTask(flightDao).execute(flight)
    }

    fun getFlights(): LiveData<List<Flight>> {
        return flightDao?.getFlights() ?: MutableLiveData<List<Flight>>()
    }


    private class InsertAsyncTask(private val flightDao: FlightDao) :
        AsyncTask<Flight, Void, Void>() {
        override fun doInBackground(vararg flights: Flight?): Void? {
            for (flight in flights) {
                if (flight != null) flightDao.insert(flight)
            }
            return null
        }
    }

}