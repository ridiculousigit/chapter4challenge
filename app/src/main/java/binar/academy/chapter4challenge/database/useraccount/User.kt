package binar.academy.chapter4challenge.database.useraccount

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
        val id: Int,
    @ColumnInfo(name = "username")
        val username : String,
    @ColumnInfo(name = "email")
        val email : String,
    @ColumnInfo(name = "password")
        val password : String
) : Parcelable