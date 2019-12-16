package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.reciclerView.Reservations
import com.example.pruebaslogin.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_edit_user.*

class MyFlightActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_flight)

        setSupportActionBar(toolbar);

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        var usersList=arrayListOf<Reservations>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewReservations)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerStudents = GridLayoutManager(this, 1)
        recyclerView.setLayoutManager(layoutManagerStudents)

        RequestHttp.getAllReserver(this,usersViewModel,usersList,recyclerView)

        val navView = findViewById(R.id.nav_view) as BottomNavigationView
        navView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_search -> {
                        val b = Intent(this@MyFlightActivity, SearchActivity::class.java)
                        startActivity(b)
                    }
                    R.id.navigation_user -> {
                        val b = Intent(this@MyFlightActivity, LoggedInActivity::class.java)
                        startActivity(b)
                    }
                }
                return false
            }
        })
    }

    override fun onPrepareOptionsMenu(menu : Menu):Boolean {
        //Se accede al ítem usando el id que
        //tiene dentro del menú directamente
        var opcion1 = menu.findItem(R.id.information_menu);
        opcion1.setEnabled(true);

        var opcion2 = menu.findItem(R.id.configuration_menu);
        opcion2.setEnabled(true);

        return true;
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.three_dots_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.information_menu) {
            val b = Intent(this, SearchActivity::class.java)
            startActivity(b)
            return true
        } else if (id == R.id.configuration_menu) {
            val b = Intent(this, ConfigurationActivity::class.java)
            startActivity(b)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
