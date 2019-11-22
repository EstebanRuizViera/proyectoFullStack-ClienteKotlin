package com.example.flight.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flight.dao.AirportDao
import com.example.flight.model.Airport
import com.example.pruebaslogin.FlightDatabase

class AirportRepository(application: Application) {

    private val airportDao: AirportDao? = FlightDatabase.getInstance(application)?.airportDao()

    fun insert(airport: Airport) {
        if (airportDao != null) InsertAsyncTask(airportDao).execute(airport)
    }

    fun getAirports(): LiveData<List<Airport>> {
        return airportDao?.getAirports() ?: MutableLiveData<List<Airport>>()
    }

    fun getAirport(country:String):String {

        if (airportDao != null)
            return SelectAsyncTask(airportDao!!).execute(country).get()
        return ""
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

    private class SelectAsyncTask(private val airportDao: AirportDao) :
        AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg countrys: String?): String? {
            for (country in countrys) {
                if (country != null)
                    return airportDao?.getAirport(country) ?:String.toString()
            }
            return null
        }
    }
}