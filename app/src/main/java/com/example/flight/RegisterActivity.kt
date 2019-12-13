package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.dni_editText
import kotlinx.android.synthetic.main.activity_register.email_editText
import kotlinx.android.synthetic.main.activity_register.lastname_editText
import kotlinx.android.synthetic.main.activity_register.name_editText
import kotlinx.android.synthetic.main.activity_register.toolbar
import kotlinx.android.synthetic.main.activity_search.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(toolbar);

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }
        accep_register_button.setOnClickListener(){
            RequestHttp.registerUser(this,name_editText,lastname_editText,dni_editText,telephone_editText,email_editText,password_editText)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
