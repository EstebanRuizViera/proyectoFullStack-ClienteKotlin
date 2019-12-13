package com.example.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.UsersViewModel
import kotlinx.android.synthetic.main.activity_edit_user.*

class EditUserActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        update_button.setOnClickListener(){
            RequestHttp.updateUser(this,usersViewModel,name_editText,lastname_editText,dni_editText,telephone_editText,email_editText)
        }
    }
}
