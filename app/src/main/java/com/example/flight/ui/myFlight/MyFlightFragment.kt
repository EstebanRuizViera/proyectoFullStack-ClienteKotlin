package com.example.flight.ui.myFlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flight.R

class MyFlightFragment : Fragment() {

    private lateinit var myFlightFragmentViewModel: MyFlightViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFlightFragmentViewModel =
            ViewModelProviders.of(this).get(MyFlightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_flight, container, false)
        val textView: TextView = root.findViewById(R.id.text_flight)
        myFlightFragmentViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}