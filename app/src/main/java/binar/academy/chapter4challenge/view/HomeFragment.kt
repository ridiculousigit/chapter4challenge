package binar.academy.chapter4challenge.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import binar.academy.chapter4challenge.R
import binar.academy.chapter4challenge.adapter.NotesAdapter
import binar.academy.chapter4challenge.database.Notes
import binar.academy.chapter4challenge.databinding.FragmentHomeBinding
import binar.academy.chapter4challenge.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var notesVM : NotesViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS_NAME = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 0)
        editor = sharedPreferences.edit()

        setRecyclerView()
        notesVM = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        notesVM.getAllNotesObserver().observe(viewLifecycleOwner) {
            notesAdapter.setNotes(it as ArrayList<Notes>)
            notesAdapter.notifyDataSetChanged()
        }

        binding.btnCreate.setOnClickListener {
            val addDialog = AddDialogFragment()
            addDialog.show(parentFragmentManager, "addDialog")
        }

        binding.btnLogout.setOnClickListener {
            editor.putString("username", "")
            editor.putBoolean("isLogin",false)
            editor.apply()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.username = sharedPreferences.getString("username","")
    }

    fun setRecyclerView() {
        notesAdapter = NotesAdapter()
        binding.recylerView.adapter = notesAdapter
        binding.recylerView.layoutManager = GridLayoutManager(requireContext(), 2)
        notesAdapter.onDeleteclick = {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete")
                .setMessage("Are you sure want to delete this note?")
                .setPositiveButton("Yes") { dialog, which ->
                    notesVM.deleteNotes(it)
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
        notesAdapter.onEditClick = {
            val editDialog = UpdateDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("oldNote", it)
            editDialog.arguments = bundle
            editDialog.show(parentFragmentManager, "editDialog")
        }
    }
}