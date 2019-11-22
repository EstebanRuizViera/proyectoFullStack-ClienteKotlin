package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }
        accep_register_button.setOnClickListener(){
            addUser()
        }
    }
    private fun addUser() {

        // User input
        val loginJsonobj = JSONObject()

        loginJsonobj.put("name", name_editText.text)
        loginJsonobj.put("lastname", lastname_editText.text)
        loginJsonobj.put("dni", dni_editText.text)
        loginJsonobj.put("email", email_editText.text)
        loginJsonobj.put("password", password_editText.text)

        // new Volley newRequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.200:8000/api/register"
        val req = object : JsonObjectRequest(
            Request.Method.POST, url, loginJsonobj,
            Response.Listener {

                usersViewModel.saveUser(User(name_editText.text.toString(),lastname_editText.text.toString(),dni_editText.text.toString(),null,email_editText.text.toString(),password_editText.text.toString(),""))

                Toast.makeText(this, "Login succesfull ! ", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener {
                Toast.makeText(this, "email or password wrong !", Toast.LENGTH_SHORT).show()
            })
        {}

        queue.add(req)



    }
}
