package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }
        accep_register_button.setOnClickListener(){
            RequestHttp.registerUser(this,usersViewModel,name_editText,lastname_editText,dni_editText,email_editText,password_editText)
        }
    }
}
