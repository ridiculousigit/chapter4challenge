package binar.academy.chapter4challenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter4challenge.R

class UpdateDialogFragment : Fragment() {
    private lateinit var binding : UpdateDialogBinding
    private lateinit var notesVM : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UpdateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesVM = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        val data = getData()

        binding.oldNotes = data
        binding.btnUpdate.setOnClickListener {
            val addedNote = Notes(
                data.id,
                binding.etEditTitle.text.toString(),
                binding.etEditContent.text.toString()
            )
            notesVM.updateNotes(addedNote)
            dismiss()
        }

    }

    private fun getData(): Notes {
        return arguments?.getParcelable("oldNote")!!
    }
}