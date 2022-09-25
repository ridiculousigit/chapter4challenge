package binar.academy.chapter4challenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import binar.academy.chapter4challenge.database.NotesDatabase
import binar.academy.chapter4challenge.database.useraccount.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UserViewModel(app : Application) : AndroidViewModel(app) {

    var userAccount : MutableLiveData<List<User>>

    init {
        userAccount = MutableLiveData()
    }

    fun identifiedAccount(email : String, password : String) : LiveData<User> {
        return NotesDatabase.getInstance((getApplication()))!!.userDao().identifyAccount(email, password)
    }

    fun gotdataAccount(user : User){
        GlobalScope.async {
            val userDAO = NotesDatabase.getInstance(getApplication())?.userDao()!!
            userDAO.insertUser(user)
        }
    }
}