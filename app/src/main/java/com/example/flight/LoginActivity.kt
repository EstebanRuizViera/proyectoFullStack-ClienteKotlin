package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UserDao
import com.example.pruebaslogin.UsersDatabase
import com.example.pruebaslogin.UsersViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private var db: UsersDatabase? = null
    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usersViewModel = run {
              ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }
        login_button.setOnClickListener(){
            login()

        }



    }

    private fun addUser() {

        var token:String= usersViewModel.getUser("asdf@asdf.com")
        Toast.makeText(this, "Token " + token, Toast.LENGTH_LONG).show()
    }

    fun login() {

        // User input
        val loginJsonobj = JSONObject()

        loginJsonobj.put("email", email_login.text)
        loginJsonobj.put("password", password_login.text)

        // new Volley newRequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.200:8000/api/login"
        val req = object : JsonObjectRequest(
            Request.Method.POST, url, loginJsonobj,
            Response.Listener {
                token=it.getString("token")

                Observable.fromCallable {
                    db = UsersDatabase.getInstance(context = this)
                    //db?.userDao()?.insert(User("Usuario","user","1234",null,"asdf@asdf.com","asdfasd",""))
                    db?.userDao()?.updateToken(email_login.text.toString(),token)

                }.doOnNext({}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()

                addUser()
            },
            Response.ErrorListener {
                Toast.makeText(this, "email or password wrong !", Toast.LENGTH_SHORT).show()
            })
        {}

        queue.add(req)




    }

}
