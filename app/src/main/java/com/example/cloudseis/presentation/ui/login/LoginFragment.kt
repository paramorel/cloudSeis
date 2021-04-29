package com.example.cloudseis.presentation.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.databinding.FragmentLoginBinding
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.network.Resource
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.enable
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity
import com.example.cloudseis.presentation.ui.registrars.RegistrarsFragment
import com.example.cloudseis.presentation.ui.startNewActivity
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.signInButton.enable(true)

//            viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
//            binding.progressbar.visible(it is Resource.Loading)
//            when (it) {
//                is Resource.Success -> {
//                    lifecycleScope.launch {
//                        viewModel.saveAuthToken(it.value.user.access_token!!)
//                        requireActivity().startNewActivity(NavigationActivity::class.java)
//                    }
//                }
//                is Resource.Failure -> handleApiError(it) { login() }
//            }
//        })

        binding.signInButton.setOnClickListener {
            login()
        }

        binding.signUpButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        activity?.let {
            Log.i("login fragment", "register")
            if (it is NavigationActivity){
                (it as NavigationActivity).openFragment(RegisterFragment())
            } else{
                (it as AuthActivity).registerFragmentEnabled()
            }
        }
    }


    private fun login(){
        val login = binding.editTextLogin.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val loginInfo = LoginInfo(login, password)
        viewModel.login(loginInfo)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resource.Success ->{
                    lifecycleScope.launch {
                        activity.let {
                            (it as NavigationActivity).openFragment(RegistrarsFragment());
                        }
                        viewModel.saveAuthToken(it.value.token!!) ///удалить при тестировании
                        Log.i("login fragment", it.value.token)
                        requireActivity().startNewActivity(NavigationActivity::class.java)
                    }
                }
                is Resource.Failure -> {
                    val toast = Toast.makeText(context, "Проверьте правильность введенных данных",
                        Toast.LENGTH_LONG)
                    toast.show()
                    binding.textPassword.setText("")
                }
            }
        })

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}
