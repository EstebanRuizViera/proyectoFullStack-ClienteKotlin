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
        const val URL ="http://192.168.103.200:8000"

        @JvmStatic private fun getTokenUser(context: Context,usersViewModel: UsersViewModel) {

            /*val listUser=usersViewModel.users
            if(listUser != null){
                for(i in 0 until listUser.size){
                    if(listUser[i].token!=""){
                        Log.println(Log.INFO,null,"token "+listUser[i].token)
                        //return listUser[i].token
                    }
                }
            }else{
                Log.println(Log.INFO,null,"get token user")
            }
*/
            //return ""
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
            val listUser =usersViewModel.users?.value


            if(listUser != null){
                listUser.forEach {
                    if(it.token!=""){
                        usersViewModel.updateToken(User(it.email, ""))
                        Log.println(Log.INFO,null,"usuario "+it.email+" ya no esta logeado")
                    }else{
                        Log.println(Log.INFO,null,"else list user logout")
                    }
                }

            }else{
                Log.println(Log.INFO,null,"else logout")
            }
            //RequestHttp.getTokenUser(context,usersViewModel)
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