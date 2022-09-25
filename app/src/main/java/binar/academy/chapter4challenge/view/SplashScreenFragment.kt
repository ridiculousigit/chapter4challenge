package binar.academy.chapter4challenge.view

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

class SplashScreenFragment : Fragment() {

    private lateinit var binding : FragmentSplashScreenBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS_NAME = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userVM = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        Handler(Looper.myLooper()!!).postDelayed({
            checkIsLogin()
        }, 2000)

    }

    fun checkIsLogin(){
        if(sharedPreferences.getBoolean("isLogin",false)){
            navigateToHome()
        }else{
            navigateToLogin()
        }
    }

    fun navigateToLogin(){
        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
    }

    fun navigateToHome(){
        findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
    }
}