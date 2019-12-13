package com.example.flight

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val navView = findViewById(R.id.nav_view) as BottomNavigationView
        navView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_my_flight -> {
                        val b = Intent(this@SearchActivity, MyFlightActivity::class.java)
                        startActivity(b)
                    }
                    R.id.navigation_user -> {
                        val b = Intent(this@SearchActivity, LoggedInActivity::class.java)
                        startActivity(b)
                    }
                }
                return false
            }
        })

        search_button.setOnClickListener(){
            val intent = Intent(this,SelectFlight::class.java)
            startActivity(intent)
        }
    }

    override fun onPrepareOptionsMenu(menu :Menu):Boolean {
        //Se accede al ítem usando el id que
        //tiene dentro del menú directamente
        var opcion1 = menu.findItem(R.id.information_menu);
        opcion1.setEnabled(true);

        return true;
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.three_dots_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.navigation_search) {
            val b = Intent(this, SearchActivity::class.java)
            startActivity(b)
            return true
        } else if (id == R.id.navigation_my_flight) {
            val b = Intent(this, MyFlightActivity::class.java)
            startActivity(b)
            return true
        }else if (id == R.id.navigation_user) {
            val b = Intent(this, LoginActivity::class.java)
            startActivity(b)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
