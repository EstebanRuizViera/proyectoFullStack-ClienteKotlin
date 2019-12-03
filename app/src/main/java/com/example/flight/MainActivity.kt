package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flight.myDataBase.viewModel.AirportViewModel
import com.example.flight.myDataBase.viewModel.FlightViewModel
import com.example.pruebaslogin.UsersViewModel
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
    }

}
