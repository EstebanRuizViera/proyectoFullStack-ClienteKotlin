package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

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
                        val b = Intent(this@SearchActivity, LogOutActivity::class.java)
                        startActivity(b)
                    }
                }
                return false
            }
        })

        RequestHttp.logout(this,usersViewModel);
    }
}
