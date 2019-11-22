package com.example.pruebaslogin

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UsersRepository(application: Application) {
    private val userDao: UserDao? = UsersDatabase.getInstance(application)?.userDao()

    fun insert(user: User) {
        if (userDao != null) InsertAsyncTask(userDao).execute(user)
    }

    fun getUsers(): LiveData<List<User>> {
        return userDao?.getUsers() ?: MutableLiveData<List<User>>()
    }

    private class InsertAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user != null) userDao.insert(user)
            }
            return null
        }
    }
}