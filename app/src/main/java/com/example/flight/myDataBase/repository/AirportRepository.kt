package com.example.flight.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flight.myDataBase.dao.AirportDao
import com.example.flight.myDataBase.model.Airport
import com.example.pruebaslogin.FlightDatabase

class AirportRepository(application: Application) {

    private val airportDao: AirportDao? = FlightDatabase.getInstance(application)?.airportDao()

    fun insert(airport: Airport) {
        if (airportDao != null) InsertAsyncTask(airportDao).execute(airport)
    }

    fun getAirports(): LiveData<List<Airport>> {
        return airportDao?.getAirports() ?: MutableLiveData<List<Airport>>()
    }

    private class InsertAsyncTask(private val airportDao: AirportDao) :
        AsyncTask<Airport, Void, Void>() {
        override fun doInBackground(vararg airports: Airport?): Void? {
            for (airport in airports) {
                if (airport != null) airportDao.insert(airport)
            }
            return null
        }
    }
}