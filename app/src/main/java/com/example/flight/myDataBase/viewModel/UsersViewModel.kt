package com.example.pruebaslogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsersRepository(application)
    //val users = repository.getUsers()
    val users=MainViewModel(application)

    fun saveUser(user: User) {
        repository.insert(user)
    }

    fun updateToken(user: User) {
        repository.updateToken(user)
    }

    fun deleteUser(user: User) {
        repository.deleteUsers(user)
    }

    fun getToken(email_user: String):String {
        return repository.getToken(email_user)
    }

    fun getUser(email_user: String):String {
        return repository.getUser(email_user)
    }

    class MainViewModel(application: Application) : ViewModel() {

        private val repository = UsersRepository(application)
        val users : LiveData<List<User>> get() = repository.getUsers()!!

        override fun onCleared() {
            super.onCleared()

        }
    }
}