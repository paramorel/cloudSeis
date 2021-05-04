package com.example.cloudseis.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.databinding.FragmentRegistrationBinding
import com.example.cloudseis.network.Answer
import com.example.cloudseis.presentation.ui.handleApiError
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity


class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegistrationBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val token = arguments?.getString("token")
//        if (token != null){
//            confirmRegister(token)
//            return
//        }

        binding.registerButton.setOnClickListener {
            val username = binding.editTextTextLogin.text.toString().trim()
            val firstname = binding.editTextFirstName.text.toString().trim()
            val lastname = binding.editTextLastName.text.toString().trim()
            val organization = binding.editTextOrganization.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPass = binding.editTextConfirmPassword.text.toString().trim()

            if (username.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() &&
                    organization.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass){
                    register(username, firstname, lastname, organization, password)
                } else{
                    val toast = Toast.makeText(it.context, "Пароли не совпадают!",
                        Toast.LENGTH_LONG)
                    toast.show()
                    binding.editTextPassword.setText("")
                    binding.editTextConfirmPassword.setText("")
                }
            } else{
                val toast = Toast.makeText(it.context, "Заполните все поля!",
                    Toast.LENGTH_LONG)
                toast.show()
            }
        }
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
    private fun register(username: String, firstname: String, lastname: String, organization: String, password: String){
        val registrationInfo = RegistrationInfo(username, password, firstname, lastname, organization)
        viewModel.register(registrationInfo)
        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            when (it){
                is Answer.Success -> {
                    val toast = Toast.makeText(context, "Успешная регистрация. Необходимо авторизироваться",
                        Toast.LENGTH_LONG)
                    toast.show()
                    binding.editTextTextLogin.setText("")
                    binding.editTextFirstName.setText("")
                    binding.editTextLastName.setText("")
                    binding.editTextOrganization.setText("")
                    binding.editTextPassword.setText("")
                    binding.editTextConfirmPassword.setText("")

                    login()
                }
                is Answer.Failure ->handleApiError(it) {
                    val toast = Toast.makeText(context, "Произошла ошибка. Попробуйте позже",
                        Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        })
    }

    private fun login() {
        activity?.let {
            if (it is NavigationActivity) {
                (it as NavigationActivity).openFragment(LoginFragment())
            } else {
                (it as AuthActivity).loginFragmentEnabled()
            }
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegistrationBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}
