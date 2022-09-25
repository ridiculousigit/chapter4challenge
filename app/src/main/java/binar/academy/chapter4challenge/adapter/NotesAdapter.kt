package binar.academy.chapter4challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.academy.chapter4challenge.database.Notes
import binar.academy.chapter4challenge.databinding.NotesItemBinding

class class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    var onDeleteclick: ((Notes) -> Unit)? = null
    var onEditClick : ((Notes) -> Unit)? = null
    var listNotes = ArrayList<Notes>()

    class ViewHolder(var binding : NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notesData = listNotes[position]
        holder.binding.notesData = notesData
//        holder.binding.btnEdit.setOnClickListener {
//            onEditClick?.invoke(notesData)
//        }
//        holder.binding.btnDelete.setOnClickListener {
//            onDeleteclick?.invoke(notesData)
//        }

        holder.binding.cvNotes.setOnLongClickListener {
            onDeleteclick?.invoke(notesData)
            true
        }

        holder.binding.cvNotes.setOnClickListener {
            onEditClick?.invoke(notesData)
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setNotes(notes: ArrayList<Notes>) {
        this.listNotes = notes
    }
}