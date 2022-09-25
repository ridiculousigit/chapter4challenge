package binar.academy.chapter4challenge.database

import androidx.room.*

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Notes>

    @Insert
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)
}