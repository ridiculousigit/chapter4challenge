package binar.academy.chapter4challenge.view.main

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import binar.academy.chapter4challenge.R
import binar.academy.chapter4challenge.adapter.NotesAdapter
import binar.academy.chapter4challenge.room.notelist.Notes
import binar.academy.chapter4challenge.databinding.FragmentHomeBinding
import binar.academy.chapter4challenge.view.crud.CreateDialogFragment
import binar.academy.chapter4challenge.view.crud.UpdateDialogFragment
import binar.academy.chapter4challenge.viewmodel.NoteViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : NotesAdapter
    private lateinit var notesVM : NoteViewModel
    private lateinit var sharedPref : SharedPreferences
    private lateinit var sharedEditor : SharedPreferences.Editor
    private val preferences = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(preferences, 0)
        sharedEditor = sharedPref.edit()

        setAlertDialog()
        notesVM = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
        notesVM.gotdataNotesObserver().observe(viewLifecycleOwner) {
            adapter.setNotes(it as ArrayList<Notes>)
            adapter.notifyDataSetChanged()
        }

        binding.btnCreate.setOnClickListener {
            val addDialog = CreateDialogFragment()
            addDialog.show(parentFragmentManager, "addDialog")
        }

        binding.btnLogout.setOnClickListener {
            sharedEditor.putString("username", "")
            sharedEditor.putBoolean("isLogin",false)
            sharedEditor.apply()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.dataUsername = sharedPref.getString("username","")
    }

    private fun setAlertDialog() {
        adapter = NotesAdapter()
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.setOnDelete = {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Notes")
                .setMessage("Are you sure you want to delete this item ?")
                .setPositiveButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .setNegativeButton("Yes") { dialog, which ->
                    notesVM.deleteNotes(it)
                    Toast.makeText(context,"Your item has been deleted !", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        adapter.setOnUpdate = {
            val customDialog = UpdateDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("pastNotes", it)
            customDialog.arguments = bundle
            customDialog.show(parentFragmentManager, "editDialog")
        }
    }
}