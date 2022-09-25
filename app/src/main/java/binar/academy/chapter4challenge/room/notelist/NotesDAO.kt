package binar.academy.chapter4challenge.room.notelist

import androidx.room.*

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun gotlistNotes(): List<Notes>

    @Insert
    fun createNotes(note: Notes)

    @Update
    fun updateNotes(note: Notes)

    @Delete
    fun deleteNotes(note: Notes)
}