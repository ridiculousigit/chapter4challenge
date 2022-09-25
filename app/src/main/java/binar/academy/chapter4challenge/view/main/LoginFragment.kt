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
import binar.academy.chapter4challenge.databinding.FragmentLoginBinding
import binar.academy.chapter4challenge.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val preferences = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // It will fetch by default the related activity
        userVM = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        sharedPreferences = requireActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        optionSignUp()
    }

    private fun optionSignUp() {
        // Button Sign In
        binding.btnLogin.setOnClickListener {
            val emailUser = binding.etEmaillog.text.toString()
            val passUser = binding.etPasslog.text.toString()
            identifyAccount(emailUser, passUser)
        }

        // Option Sign Up
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    // Method for identifying whether the email/password is correct or not
    private fun identifyAccount(emailUser : String, passUder : String) {
        userVM.identifiedAccount(emailUser, passUder).observe(viewLifecycleOwner) {
            when (it) {
                null -> {
                    Toast.makeText(requireContext(), "Fill all the fields with correct answer !", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    editor.putString("username", it.username)
                    editor.putBoolean("isLogin", true)
                    editor.apply()
                    directtoHome()
                }
            }
        }
    }

    // It will redirect to Home Fragment
    private fun directtoHome(){
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}