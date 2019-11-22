package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        login_activity_button.setOnClickListener(){
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        register_activity_button.setOnClickListener(){
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        comprobar_button.setOnClickListener(){
            comprobarName()
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
}
