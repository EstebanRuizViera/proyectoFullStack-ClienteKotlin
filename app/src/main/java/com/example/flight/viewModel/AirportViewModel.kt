package com.example.flight.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.flight.model.Airport
import com.example.flight.repository.AirportRepository

class AirportViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = AirportRepository(application)
    val airports = repository.getAirports()

    fun saveAirport(airport: Airport) {
        repository.insert(airport)
    }

}