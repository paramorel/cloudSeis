package com.example.cloudseis.presentation.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cloudseis.R

class AuthActivity : AppCompatActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        navController = Navigation.findNavController(this, R.id.fragment)

//        if (intent.action.toString() == "android.intent.action.VIEW"){
//            val uri  = intent.data
//            val token = uri?.getQueryParameter("token")
//
//            val bundle = Bundle()
//            bundle.putString("token", token)
//            navController?.navigate(R.id.registrationFragment, bundle)
//        }
    }


    fun registerFragmentEnabled(){
        navController?.navigate(R.id.registrationFragment)
    }

    fun loginFragmentEnabled(){
        navController?.navigate(R.id.loginFragment)
    }
}
