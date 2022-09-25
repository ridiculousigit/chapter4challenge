package binar.academy.chapter4challenge.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4challenge.R
import binar.academy.chapter4challenge.databinding.FragmentSplashScreenBinding
import binar.academy.chapter4challenge.viewmodel.UserViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var binding : FragmentSplashScreenBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor
    private val preferences = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // It will fetch by default the related activity
        userVM = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        sharedPref = requireActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE)
        sharedEditor = sharedPref.edit()

        // Utility class for showing splash screen
        Handler(Looper.myLooper()!!).postDelayed({
            isLogin()
        }, 3000)

    }

    // Method to checking whether the user is logged in or not
    private fun isLogin(){
        when {
            sharedPref.getBoolean("isLogin", false) -> {
                directtoHome()
            }
            else -> {
                directoLogin()
            }
        }
    }

    // It will redirect to recent condition or Login Fragment if u didnt sign in before
    private fun directoLogin() = findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
    private fun directtoHome() = findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
}