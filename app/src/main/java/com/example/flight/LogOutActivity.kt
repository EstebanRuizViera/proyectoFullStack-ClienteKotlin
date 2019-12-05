package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class LogOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_out)

        val navView = findViewById(R.id.nav_view) as BottomNavigationView
        navView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_search -> {
                        val b = Intent(this@LogOutActivity, SearchActivity::class.java)
                        startActivity(b)
                    }
                    R.id.navigation_my_flight -> {
                        val b = Intent(this@LogOutActivity, MyFlightActivity::class.java)
                        startActivity(b)
                    }
                }
                return false
            }
        })
    }
}
