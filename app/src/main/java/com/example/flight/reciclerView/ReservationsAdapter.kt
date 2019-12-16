package com.example.flight.reciclerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.LoginActivity
import com.example.flight.R

class ReservationsAdapter(reservations: ArrayList<Reservations>, private val context: Context) :
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder?>() {

    var reservations: ArrayList<Reservations>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.reservations_layout, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.origin_textView.setText(reservations[position].origen)
        holder.destiny_texView.setText(reservations[position].destino)
        holder.departure_date_textView.setText(reservations[position].fechaDeIda)
        holder.arrival_date_textView.setText(reservations[position].fechaDeVuelta)
        holder.linearLayout.setOnClickListener(){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var origin_textView: TextView
        var destiny_texView: TextView
        var departure_date_textView: TextView
        var arrival_date_textView: TextView
        lateinit var linearLayout: LinearLayout

        init {
            origin_textView = v.findViewById(R.id.origen_textView)
            destiny_texView = v.findViewById(R.id.destiny_textView)
            departure_date_textView = v.findViewById(R.id.departure_date_textView)
            arrival_date_textView = v.findViewById(R.id.arrival_date_textView)

            linearLayout = v.findViewById(R.id.linearLayout)
        }
    }

    init {
        this.reservations = reservations
    }
}