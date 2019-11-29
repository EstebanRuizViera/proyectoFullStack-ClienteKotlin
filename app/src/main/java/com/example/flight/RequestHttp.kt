package com.example.flight

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebaslogin.FlightDatabase
import com.example.pruebaslogin.User
import com.example.pruebaslogin.UsersViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject

class RequestHttp {

    companion object {
        private var db: FlightDatabase? = null

        @JvmStatic fun syncUpDataBase(context: Context,usersViewModel:UsersViewModel){

            getTokenUser(usersViewModel)

            var array= JSONArray()

            // new Volley newRequestQueue
            val updateQueue = Volley.newRequestQueue(context)
            val updateUrl = "http://192.168.103.200:8000/api/users"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, updateUrl, null,
                Response.Listener {
                    Toast.makeText(context, "Succesful access", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, "Error of syncronization", Toast.LENGTH_SHORT).show()
                })
            {
                // override getHeader for pass session to service
                override fun getHeaders(): MutableMap<String, String> {

                    val header = mutableMapOf<String, String>()
                    try {
                        header.put("Content-Type", "application/json")
                        header.put("Authorization", "Bearer " + getTokenUser(usersViewModel))
                    } catch (e: StackOverflowError) {
                        Log.println(Log.INFO,null,e.message)
                    }
                    return header
                }
            }
            updateQueue.add(updateReq)

        }

        @JvmStatic private fun getTokenUser(usersViewModel: UsersViewModel):String {

            val listUser=usersViewModel.users.value
            if(listUser != null){
                for(i in 0 until listUser!!.size){
                    if(listUser[i].token!=""){
                        return listUser[i].token
                    }
                }
            }else{
                Log.println(Log.INFO,null,"else")
            }

            return ""
        }

        @JvmStatic fun login(context: Context,email_login:EditText,password_login:EditText,usersViewModel: UsersViewModel) {

            var token:String?=null
            // User input
            val loginJsonobj = JSONObject()

            loginJsonobj.put("email", email_login.text)
            loginJsonobj.put("password", password_login.text)

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = "http://192.168.103.200:8000/api/login"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    token=it.getString("token")

                    Observable.fromCallable {
                        db = FlightDatabase.getInstance(context = context)
                        //db?.userDao()?.insert(User("Usuario","user","1234",null,"asdf@asdf.com","asdfasd",""))
                        db?.userDao()?.updateToken(email_login.text.toString(),token!!)

                    }.doOnNext({}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()

                    Toast.makeText(context, "Succesful login", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "email or password wrong !", Toast.LENGTH_SHORT).show()
                })
            {}

            queue.add(req)

        }

        fun addUser(context: Context,usersViewModel: UsersViewModel,name_editText:EditText,lastname_editText:EditText,dni_editText:EditText,email_editText:EditText,password_editText:EditText) {

            // User input
            val loginJsonobj = JSONObject()

            loginJsonobj.put("name", name_editText.text)
            loginJsonobj.put("lastname", lastname_editText.text)
            loginJsonobj.put("dni", dni_editText.text)
            loginJsonobj.put("email", email_editText.text)
            loginJsonobj.put("password", password_editText.text)

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = "http://192.168.103.200:8000/api/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    usersViewModel.saveUser(User(email_editText.text.toString(),""))
                    Toast.makeText(context, "Login succesfull ! ", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "email or password wrong !", Toast.LENGTH_SHORT).show()
                })
            {}

            queue.add(req)
        }

        fun sincronizacion(context: Context,usersViewModel: UsersViewModel){

            var array=JSONArray()

            // new Volley newRequestQueue
            val updateQueue = Volley.newRequestQueue(context)
            val updateUrl = "http://192.168.103.200:8000/api/users"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, updateUrl, null,
                Response.Listener {
                    Toast.makeText(context, "Succesful access", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, "Error of authentication", Toast.LENGTH_SHORT).show()
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

}