package com.example.flight

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
        const val URL ="http://192.168.103.200:8000"

        @JvmStatic fun getTokenUser(context: Context,usersViewModel: UsersViewModel)  {

            var objectToken ="void"
            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/all/users"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    for(i in 0 until it.length()){

                        if(usersViewModel.getToken(it.getJSONObject(i).getString("email"))== ""){
                            Log.println(Log.INFO,null,"token vacio")
                        }else{

                            val intent = Intent(context,MyFlightActivity::class.java)
                            context.startActivity(intent)
                            Log.println(Log.INFO,null,"get token "+objectToken)
                        }
                    }
                },
                Response.ErrorListener {
                    Log.println(Log.INFO,null,"error al obtener el token")
                }
            ){}

            queue.add(updateReq)

        }

        @JvmStatic fun login(context: Context,email_text:EditText,password_text:EditText,usersViewModel: UsersViewModel) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("email", email_text.text)
            loginJsonobj.put("password", password_text.text)

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/login"
            val req = object : JsonObjectRequest(Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    usersViewModel.updateToken(User(email_text.text.toString(), it.getString("token")))
                    Log.println(Log.INFO, null, "if "+usersViewModel.getUser(email_text.text.toString()))

                    val token=usersViewModel.getToken(email_text.text.toString())
                    Toast.makeText(context, "Identificacion correcta "+token, Toast.LENGTH_SHORT).show()

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al identificarte", Toast.LENGTH_SHORT).show()
                }
            ){}
            queue.add(req)
        }

        @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){


            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/all/users"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    for(i in 0 until it.length()){
                        var obj=it.getJSONObject(i)
                        usersViewModel.updateToken(User(obj.getString("email"), ""))
                        Log.println(Log.INFO,null,"usuario "+obj.getString("email")+" ya no esta logeado")
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error of authentication", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)

        }

        @JvmStatic fun registerUser(context: Context,usersViewModel: UsersViewModel,name_editText:EditText,lastname_editText:EditText,dni_editText:EditText,email_editText:EditText,password_editText:EditText) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("name", name_editText.text)
            loginJsonobj.put("lastname", lastname_editText.text)
            loginJsonobj.put("dni", dni_editText.text)
            loginJsonobj.put("email", email_editText.text)
            loginJsonobj.put("password", password_editText.text)

            val queue = Volley.newRequestQueue(context)
            val url = URL +"/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {

                    usersViewModel.saveUser(User(email_editText.text.toString(),""))

                    Toast.makeText(context, "Registro realizado ! ", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "error al registrar !", Toast.LENGTH_SHORT).show()
                })
            {}

            queue.add(req)
        }
        @JvmStatic fun sincronizacionUsuarios(context: Context,usersViewModel: UsersViewModel){

            var array= JSONArray()

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL+"/api/all/users"
            val updateReq = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    Toast.makeText(context, "Succesful access", Toast.LENGTH_SHORT).show()
                    array=it
                    for (i in 0 until array.length()) {
                        val user = array.getJSONObject(i)
                        if(usersViewModel.getUser(user.getString("email"))== ""){
                            usersViewModel.saveUser(User(user.getString("email"),""))
                            Log.println(Log.INFO,null,"save "+usersViewModel.getUser(user.getString("email")))
                        }else{
                            Log.println(Log.INFO,null,"dont save "+user.getString("name"))
                        }
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error of authentication", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(updateReq)

        }

    }

}