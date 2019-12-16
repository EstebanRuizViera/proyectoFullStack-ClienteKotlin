package com.example.flight

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.toolbar


class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar);

        val b = Bundle()

        val staticSpinner = findViewById(R.id.static_spinner) as Spinner

        // Create an ArrayAdapter using the string array and a default spinner
        val staticAdapter = ArrayAdapter
            .createFromResource(
                this, R.array.brew_array,
                android.R.layout.simple_spinner_item
            )

        // Specify the layout to use when the list of choices appears
        staticAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        staticSpinner.adapter = staticAdapter

        staticSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                if(parent.getItemAtPosition(position)=="Madrid"){
                    b.putString("option", "1");
                }else if(parent.getItemAtPosition(position)=="Canarias"){
                    b.putString("option", "2");
                }else if(parent.getItemAtPosition(position)=="Lisboa"){
                    b.putString("option", "3");
                }else if(parent.getItemAtPosition(position)=="Oporto"){
                    b.putString("option", "4");
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { // TODO Auto-generated method stub
            }
        })


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
            intent.putExtras(b)
            startActivity(intent)
        }
    }

    override fun onPrepareOptionsMenu(menu :Menu):Boolean {
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
