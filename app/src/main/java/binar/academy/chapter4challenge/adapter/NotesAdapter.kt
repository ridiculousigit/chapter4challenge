package binar.academy.chapter4challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.academy.chapter4challenge.room.notelist.Notes
import binar.academy.chapter4challenge.databinding.NotesItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var setOnUpdate : ((Notes) -> Unit)? = null
    var setOnDelete: ((Notes) -> Unit)? = null
    private var listNotes = ArrayList<Notes>()

    class ViewHolder(var binding : NotesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notesData = listNotes[position]
        holder.binding.notesData = notesData

        // It will delete the items if long pressed at item
        holder.binding.cvNotes.setOnLongClickListener {
            setOnDelete?.invoke(notesData)
            true
        }

        // It will pop up update form if short pressed at item
        holder.binding.cvNotes.setOnClickListener {
            setOnUpdate?.invoke(notesData)
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setNotes(notes: ArrayList<Notes>) {
        this.listNotes = notes
    }
}