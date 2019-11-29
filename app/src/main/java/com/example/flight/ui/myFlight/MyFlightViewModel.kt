package com.example.flight.ui.myFlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyFlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is 'my flight' Fragment"
    }
    val text: LiveData<String> = _text
}