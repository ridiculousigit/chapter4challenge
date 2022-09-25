package binar.academy.chapter4challenge.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun verifyUser(email : String, password : String): LiveData<User>

    @Insert
    fun insertUser(user: User)
}