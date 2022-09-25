package binar.academy.chapter4challenge.view.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter4challenge.database.notelist.Notes
import binar.academy.chapter4challenge.databinding.FragmentUpdateDialogBinding
import binar.academy.chapter4challenge.viewmodel.NoteViewModel

class UpdateDialogFragment : DialogFragment() {
    private lateinit var binding : FragmentUpdateDialogBinding
    private lateinit var vmNote : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // It will fetch by default the related activity
        vmNote = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
        val data = gotData()
        binding.pastNotes = data

        // Button Update to update notes
        binding.btnUpdate.setOnClickListener {
            val addedNote = Notes(
                data.id,
                binding.updateId.text.toString(),
                binding.updateTitle.text.toString()
            )
            vmNote.updateNotes(addedNote)
            dismiss()
        }

    }

    // Got data using Parcelable
    private fun gotData(): Notes {
        return arguments?.getParcelable("pastNotes")!!
    }
}