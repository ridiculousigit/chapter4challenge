package binar.academy.chapter4challenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import binar.academy.chapter4challenge.database.notelist.Notes
import binar.academy.chapter4challenge.database.notelist.NotesDAO
import binar.academy.chapter4challenge.database.useraccount.User
import binar.academy.chapter4challenge.database.useraccount.UserDAO

@Database(entities = [Notes::class, User::class], version = 2)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDAO
    abstract fun userDao(): UserDAO

    companion object{

        private var INSTANCE : NotesDatabase? = null

        fun getInstance(context : Context):NotesDatabase? {
            if (INSTANCE == null){
                synchronized(NotesDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NotesDatabase::class.java,"notes_taking.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}