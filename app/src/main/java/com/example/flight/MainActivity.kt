package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.flight.myDataBase.viewModel.AirportViewModel
import com.example.flight.myDataBase.viewModel.FlightViewModel
import com.example.pruebaslogin.UsersViewModel
import java.lang.Exception
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var airportViewModel: AirportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        RequestHttp.sincronizacionUsuarios(this,usersViewModel);

        val background = object : Thread(){
            override fun run(){
                try{
                    sleep(4000)
                    val intent = Intent(baseContext,SearchActivity::class.java)
                    startActivity(intent)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

}
