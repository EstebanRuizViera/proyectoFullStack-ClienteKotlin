package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_logged_in.*

class LoggedInActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        val navView = findViewById(R.id.nav_view) as BottomNavigationView
        navView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.navigation_search -> {
                        val b = Intent(this@LoggedInActivity, SearchActivity::class.java)
                        startActivity(b)
                    }
                    R.id.navigation_my_flight -> {
                        val b = Intent(this@LoggedInActivity, MyFlightActivity::class.java)
                        startActivity(b)
                    }
                }
                return false
            }
        })

        edit_button.setOnClickListener(){
            val intent= Intent(this,EditUserActivity::class.java)
            startActivity(intent)
        }
        delete_button.setOnClickListener(){
            RequestHttp.deleteUser(this,usersViewModel )
        }
        logout_button.setOnClickListener(){
            RequestHttp.logout(this,usersViewModel )
        }

        RequestHttp.selectUser(this,usersViewModel,name_text_user,lastname_editText,dni_text_user,phone_text_user,email_text_user)
    }

    override fun onPrepareOptionsMenu(menu : Menu):Boolean {
        //Se accede al ítem usando el id que
        //tiene dentro del menú directamente
        var opcion1 = menu.findItem(R.id.navigation_my_flight);
        opcion1.setEnabled(true);

        return true;
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
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
