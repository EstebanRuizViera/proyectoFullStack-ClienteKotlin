package com.example.flight.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.flight.myDataBase.model.Airport
import com.example.flight.myDataBase.repository.AirportRepository

class AirportViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = AirportRepository(application)
    val airports = repository.getAirports()

    fun saveAirport(airport: Airport) {
        repository.insert(airport)
    }

}