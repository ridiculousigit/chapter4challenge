package binar.academy.chapter4challenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import binar.academy.chapter4challenge.database.notelist.Notes
import binar.academy.chapter4challenge.database.NotesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    var datalistNotes : MutableLiveData<List<Notes>?>

    init {
        datalistNotes = MutableLiveData()
        gotdataNotes()
    }

    fun gotdataNotesObserver() : MutableLiveData<List<Notes>?> {
        return datalistNotes
    }

    fun gotdataNotes(){
        GlobalScope.launch {
            val notesDAO = NotesDatabase.getInstance((getApplication()))!!.notesDao()
            val listNotes = notesDAO.gotlistNotes()
            datalistNotes.postValue(listNotes)
        }
    }

    fun createNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesDatabase.getInstance((getApplication()))!!.notesDao()
            notesDAO.createNotes(notes)
            gotdataNotes()
        }
    }

    fun updateNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesDatabase.getInstance((getApplication()))!!.notesDao()
            notesDAO.updateNotes(notes)
            gotdataNotes()
        }
    }

    fun deleteNotes(notes : Notes){
        GlobalScope.launch {
            val notesDAO = NotesDatabase.getInstance((getApplication()))!!.notesDao()
            notesDAO.deleteNotes(notes)
            gotdataNotes()
        }
    }
}