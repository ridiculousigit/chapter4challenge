package binar.academy.chapter4challenge.view.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4challenge.R
import binar.academy.chapter4challenge.database.useraccount.User
import binar.academy.chapter4challenge.databinding.FragmentRegisterBinding
import binar.academy.chapter4challenge.viewmodel.UserViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var vmUser : UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor
    private val preferences = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmUser = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        sharedPref = requireActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE)
        sharedEditor = sharedPref.edit()

        // Button Sign Up
        binding.btnRegister.setOnClickListener {
            val userreg = binding.etUserreg.text.toString()
            val emailreg = binding.etEmailreg.text.toString()
            val passreg = binding.etPassreg.text.toString()
            val copassreg = binding.etConfirmreg.text.toString()
            when {
                identifySimilar(passreg, copassreg) -> {
                    saveUser(userreg, emailreg, passreg)
                    Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                else -> {
                    Toast.makeText(context, "Confirmation Password does not match", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Option Sign In
        binding.tvSignin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    // Method for identifying whether pass == confirm pass
    private fun identifySimilar(password: String, confirmPassword: String): Boolean = password == confirmPassword

    // Method for got user data
    private fun saveUser(username : String, email : String, password : String){
        with(this.vmUser) { gotdataAccount(User(0, username, email, password)) }
    }
}