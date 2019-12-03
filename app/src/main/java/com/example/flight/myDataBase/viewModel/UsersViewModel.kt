package com.example.pruebaslogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsersRepository(application)
    val users = repository.getUsers()

    fun saveUser(user: User) {
        repository.insert(user)
    }

    fun getUser(email_user: String):String {
        return repository.getUser(email_user)
    }
}