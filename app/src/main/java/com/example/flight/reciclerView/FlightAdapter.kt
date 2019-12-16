package com.example.flight.reciclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.R
import com.example.flight.RequestHttp
import com.example.pruebaslogin.UsersViewModel

class FlightAdapter(flights: ArrayList<Flights>, val mContext: Context,val usersViewModel: UsersViewModel) :
    RecyclerView.Adapter<FlightAdapter.ViewHolder?>() {

    var flights: ArrayList<Flights>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.flight_layout,null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.origen_texView.setText(flights[position].destino)
        holder.linearLayout.setOnClickListener(){
            RequestHttp.createReservation(mContext,usersViewModel,flights[position].id_vuelo)

        }
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var origen_texView: TextView
        lateinit var linearLayout: LinearLayout

        init {
            origen_texView = v.findViewById(R.id.origen_textView)
            linearLayout = v.findViewById(R.id.linearLayout)
        }
    }

    init {
        this.flights = flights
    }
}