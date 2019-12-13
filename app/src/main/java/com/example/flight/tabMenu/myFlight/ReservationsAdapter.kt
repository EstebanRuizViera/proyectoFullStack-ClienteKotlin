package com.example.flight.tabMenu.myFlight;

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

class ReservationsAdapter(students: ArrayList<Reservations>, private val context: Context) :
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder?>() {

    var reservations: ArrayList<Reservations>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.reservations_layout, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewName.setText(reservations[position].name)
        holder.textViewSurname.setText(reservations[position].surname)
        holder.constraintLayout.setOnClickListener(){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textViewName: TextView
        var textViewSurname: TextView
        lateinit var constraintLayout: LinearLayout

        init {
            textViewName = v.findViewById(R.id.textViewName)
            textViewSurname = v.findViewById(
                R.id.textViewSurname)
            constraintLayout = v.findViewById(R.id.constraintLayout)
        }
    }

    init {
        this.reservations = students
    }
}