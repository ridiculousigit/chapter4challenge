package binar.academy.chapter4challenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class,User::class], version = 2)
abstract class NotesTakingDB : RoomDatabase() {
    abstract fun notesDao(): NotesDAO
    abstract fun userDao(): UserDAO

    companion object{

        private var INSTANCE : NotesTakingDB? = null

        fun getInstance(context : Context):NotesTakingDB? {
            if (INSTANCE == null){
                synchronized(NotesTakingDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NotesTakingDB::class.java,"notes_taking.db")
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