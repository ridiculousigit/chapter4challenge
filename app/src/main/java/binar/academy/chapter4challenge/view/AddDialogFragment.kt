package binar.academy.chapter4challenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter4challenge.R
import binar.academy.chapter4challenge.database.Notes
import binar.academy.chapter4challenge.databinding.FragmentAddDialogBinding
import binar.academy.chapter4challenge.viewmodel.NotesViewModel

class AddDialogFragment : Fragment() {

    lateinit var binding: FragmentAddDialogBinding
    private lateinit var notesVM : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesVM = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            val addedNote : Notes = Notes(
                0,
                binding.addId.text.toString(),
                binding.addTitle.text.toString()
            )
            notesVM.insertNotes(addedNote)
            dismiss()
        }

    }

}