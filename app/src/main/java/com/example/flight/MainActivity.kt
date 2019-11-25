package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.flight.viewModel.AirportViewModel
import com.example.flight.viewModel.FlightViewModel
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray

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
        //flightViewModel = run {
          //  ViewModelProviders.of(this).get(FlightViewModel::class.java)
        //}
        //airportViewModel = run {
          //  ViewModelProviders.of(this).get(AirportViewModel::class.java)
        //}


        login_activity_button.setOnClickListener(){
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        register_activity_button.setOnClickListener(){
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        comprobar_button.setOnClickListener(){
            sincronizacion()
            //comprobarName()
        }
    }

    private fun tokenUser() {

        token= usersViewModel.getUser(editText.text.toString())
        //Toast.makeText(this, "Token " + token, Toast.LENGTH_LONG).show()
    }

    fun comprobarName(){

        // new Volley newRequestQueue
        val updateQueue = Volley.newRequestQueue(this)
        val updateUrl = "http://192.168.103.200:8000/api/user/1"
        val updateReq = object : JsonObjectRequest(
            Request.Method.GET, updateUrl, null,
            Response.Listener {
                Toast.makeText(this, "Succesful access", Toast.LENGTH_SHORT).show()
                textView3.setText(it.getString("name"))
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error of authentication", Toast.LENGTH_SHORT).show()
            }) {

            // override getHeader for pass session to service
            override fun getHeaders(): MutableMap<String, String> {

                val header = mutableMapOf<String, String>()
                try {

                    header.put("Content-Type", "application/json")
                    tokenUser()
                    header.put("Authorization", "Bearer " + token)
                } catch (e: StackOverflowError) {
                    Log.println(Log.INFO,null,e.message)
                }
                return header
            }
        }
        updateQueue.add(updateReq)
    }

    fun sincronizacion(){

        var array=JSONArray()

        // new Volley newRequestQueue
        val updateQueue = Volley.newRequestQueue(this)
        val updateUrl = "http://192.168.103.200:8000/api/users"
        val updateReq = object : JsonArrayRequest(
            Request.Method.GET, updateUrl, null,
            Response.Listener {
                Toast.makeText(this, "Succesful access", Toast.LENGTH_SHORT).show()
                array=it
                for (i in 0 until array.length()) {
                    val user = array.getJSONObject(i)
                    if(usersViewModel.getUser(user.getString("email"))!= ""){
                        usersViewModel.saveUser(User(user.getString("email"),""))
                        Log.println(Log.INFO,null,"save "+user.getString("name"))
                    }else{
                        Log.println(Log.INFO,null,"dont save "+user.getString("name"))
                    }
                }
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error of authentication", Toast.LENGTH_SHORT).show()
            }) {

            // override getHeader for pass session to service
            override fun getHeaders(): MutableMap<String, String> {

                val header = mutableMapOf<String, String>()
                try {

                    header.put("Content-Type", "application/json")
                    header.put("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjEwMy4yMDA6ODAwMFwvYXBpXC9sb2dpbiIsImlhdCI6MTU3NDcwNTcxMSwiZXhwIjoxNTc0NzA5MzExLCJuYmYiOjE1NzQ3MDU3MTEsImp0aSI6IkRWaDZHZ2N4d01aZkFzWVAiLCJzdWIiOjMsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEifQ.glar6MMFwEmUKz8XVKJ6sqbTO1uYy-nTjzvH4H9Peb4")
                } catch (e: StackOverflowError) {
                    Log.println(Log.INFO,null,e.message)
                }
                return header
            }
        }
        updateQueue.add(updateReq)


    }

}
