package binar.academy.chapter4challenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import binar.academy.chapter4challenge.database.Notes
import binar.academy.chapter4challenge.database.NotesTakingDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesViewModel(app: Application) : AndroidViewModel(app) {
    var allNotes : MutableLiveData<List<Notes>?>

    init {
        allNotes = MutableLiveData()
        getAllNotes()
    }

    fun getAllNotesObserver() : MutableLiveData<List<Notes>?> {
        return allNotes
    }

    fun getAllNotes(){
        GlobalScope.launch {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            val listNotes = notesDAO.getAllNotes()
            allNotes.postValue(listNotes)
        }
    }

    fun insertNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.insertNote(notes)
            getAllNotes()
        }
    }

    fun deleteNotes(notes : Notes){
        GlobalScope.launch {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.deleteNote(notes)
            getAllNotes()
        }
    }

    fun updateNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.updateNote(notes)
            getAllNotes()
        }
    }
}