package com.example.cloudseis.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.databinding.FragmentLoginBinding
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /////////////binding.progressbar.visible(false)
        ////////////////binding.buttonSignIn.enable(false)

//        binding.editTextTextPassword.addTextChangedListener {
//            val email = binding.editTextTextEmailAddress.text.toString().trim()
//            binding.buttonSignIn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
//        }
//
////        binding.buttonSignIn.setOnClickListener {
////            login()
////        }
//
//        binding.buttonSignUp.setOnClickListener {
//            register()
//        }
//    }
//
////    private fun login() {
////        val email = binding.editTextTextEmailAddress.text.toString().trim()
////        val password = binding.editTextTextPassword.text.toString().trim()
////        val loginInfo = LoginInfo(email, password)
////
////        viewModel.login(loginInfo)
////    }
//
//    private fun register() {
//        activity?.let {
//            (it as AuthActivity).registerFragmentEnabled()
//        }
//    }
//
//    override fun getViewModel() = AuthViewModel::class.java
//
//    override fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ) = FragmentLoginBinding.inflate(inflater, container, false)
//
//    override fun getFragmentRepository() =
//        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
//
//    fun failedLogin(message: String) {
//        val toast = Toast.makeText(context, message,
//            Toast.LENGTH_LONG)
//        toast.show()
//        binding.editTextTextPassword.setText("")
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}
