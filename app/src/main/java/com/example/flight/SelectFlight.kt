package com.example.flight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.reciclerView.Flights
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_search.toolbar
import kotlinx.android.synthetic.main.activity_select_flight.*


class SelectFlight : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_flight)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        val intent = intent
        val b = intent.extras

        setSupportActionBar(toolbar);

        recyclerViewFlights

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        var usersList=arrayListOf<Flights>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFlights)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerStudents = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerStudents)

        RequestHttp.getAllFlights(this,usersViewModel,usersList,recyclerView,b!!.getString("option")!! )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
