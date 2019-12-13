package com.example.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UsersViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val background = object : Thread(){
            override fun run(){
                try{
                    sleep(4000)
                    val intent = Intent(baseContext,LoginActivity::class.java)
                    startActivity(intent)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
