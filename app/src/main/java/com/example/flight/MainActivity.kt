package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.flight.viewModel.AirportViewModel
import com.example.flight.viewModel.FlightViewModel
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var airportViewModel: AirportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background = object : Thread(){
            override fun run(){
                try{
                    sleep(4000)
                    val intent = Intent(baseContext,PrincipalMenu::class.java)
                    startActivity(intent)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()

        /*usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }*/
        //flightViewModel = run {
          //  ViewModelProviders.of(this).get(FlightViewModel::class.java)
        //}
        //airportViewModel = run {
          //  ViewModelProviders.of(this).get(AirportViewModel::class.java)
        //}


        /*login_activity_button.setOnClickListener(){
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        register_activity_button.setOnClickListener(){
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        comprobar_button.setOnClickListener(){
            var intent = Intent(this,PrincipalMenu::class.java)
            startActivity(intent)
            //RequestHttp.sincronizacion(this,usersViewModel)
            //comprobarName()
        }*/
    }



}
