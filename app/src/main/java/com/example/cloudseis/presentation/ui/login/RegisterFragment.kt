package com.example.cloudseis.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.databinding.FragmentLoginBinding
import com.example.cloudseis.databinding.FragmentRegistrationBinding


class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegistrationBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val token = arguments?.getString("token")
//        if (token != null){
//            confirmRegister(token)
//            return
//        }

//        binding.registerButton.setOnClickListener {
//            val username = binding.editTextTextPersonName.text.toString().trim()
//            val email = binding.emailEditText.text.toString().trim()
//            val password = binding.passwordEditText.text.toString().trim()
//
//            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
//                register(username, email, password)
//            } else{
//                val toast = Toast.makeText(it.context, "Заполните все поля!",
//                    Toast.LENGTH_LONG)
//                toast.show()
//            }
//        }
    }

//    private fun confirmRegister(token: String){
//        val registerConfirmInfo = RegisterConfirmInfo(token)
//        viewModel.registerConfirm(registerConfirmInfo)
//
//        viewModel.registerConfirmResponse.observe(viewLifecycleOwner, Observer {
//            when (it){
//                is Resource.Success -> {
//                    val toast = Toast.makeText(context, "Успешное подтверждение регистрации",
//                        Toast.LENGTH_LONG)
//                    toast.show()
//                    activity?.let {
//                        (it as AuthActivity).loginFragmentEnabled()
//                    }
//                }
//                is Resource.Failure ->handleApiError(it) {
//                    val toast = Toast.makeText(context, "Произошла ошибка. Попробуйте позже",
//                        Toast.LENGTH_LONG)
//                    toast.show()
//                }
//            }
//        })
//
//    }
//
//    private fun register(username: String, email: String, password: String){
//        val registerInfo = RegisterInfo(username, email, password)
//        viewModel.register(registerInfo)
//        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
//            when (it){
//                is Resource.Success -> {
//                    val toast = Toast.makeText(context, "Проверьте почту для потдверждения регистрации",
//                        Toast.LENGTH_LONG)
//                    toast.show()
//                    binding.editTextTextPersonName.setText("")
//                    binding.emailEditText.setText("")
//                    binding.passwordEditText.setText("")
//
//                }
//                is Resource.Failure ->handleApiError(it) {
//                    val toast = Toast.makeText(context, "Произошла ошибка. Попробуйте позже",
//                        Toast.LENGTH_LONG)
//                    toast.show()
//                }
//            }
//        })
//    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegistrationBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}
