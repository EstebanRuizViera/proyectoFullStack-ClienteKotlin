package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.tabMenu.myFlight.Reservations
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.toolbar
import kotlinx.android.synthetic.main.activity_select_flight.*

class SelectFlight : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_flight)

        setSupportActionBar(toolbar);

        recyclerViewFlights

        var usersList=arrayListOf<Reservations>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFlights)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerStudents = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerStudents)

        RequestHttp.getAllFlights(this,usersList,recyclerView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
