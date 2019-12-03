package com.example.flight.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.flight.myDataBase.model.Flight
import com.example.flight.myDataBase.repository.FlightRepository

class FlightViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = FlightRepository(application)
    val flights = repository.getFlights()

    fun saveFlight(flight: Flight) {
        repository.insert(flight)
    }
}