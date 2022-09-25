package binar.academy.chapter4challenge.view.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter4challenge.database.notelist.Notes
import binar.academy.chapter4challenge.databinding.FragmentCreateDialogBinding
import binar.academy.chapter4challenge.viewmodel.NoteViewModel

class CreateDialogFragment : DialogFragment() {

    lateinit var binding: FragmentCreateDialogBinding
    private lateinit var vmNote : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmNote = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Button Add too add new notes
        binding.btnAdd.setOnClickListener {
            val createNotes = Notes(
                0,
                binding.addId.text.toString(),
                binding.addTitle.text.toString()
            )
            vmNote.createNotes(createNotes)
            dismiss()
        }

    }

}