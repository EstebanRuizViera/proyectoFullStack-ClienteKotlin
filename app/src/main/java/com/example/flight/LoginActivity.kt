package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebaslogin.FlightDatabase
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private var db: FlightDatabase? = null
    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usersViewModel = run {
              ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        if(usersViewModel.getUserIdLocal(1)==0){
            usersViewModel.saveUser(User(1,"",""))
            Log.println(Log.INFO,null,"Guardado ")
        }else{
            Log.println(Log.INFO,null,"No guardado ")
        }
        login_button.setOnClickListener(){
            RequestHttp.login(this,email_login,password_login,usersViewModel)
        }

        signuphere_tect_button.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
