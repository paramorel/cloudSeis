package com.example.cloudseis.presentation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.databinding.FragmentSettingsBinding
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.login.AuthViewModel
import kotlinx.coroutines.launch

class SettingsFragment : BaseFragment<AuthViewModel, FragmentSettingsBinding, AuthRepository>(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //binding.exitButton.visibility = View.INVISIBLE
        binding.organizationEditText.isEnabled = false
        binding.networkOwnerEdittext.isEnabled = false
        binding.editTextTextPersonName.isEnabled = false

        super.onActivityCreated(savedInstanceState)
        Log.i("settings fragment", "on activity created")

//        binding.exitButton.setOnClickListener {
//            lifecycleScope.launch {
//                logout()
//            }
//        }
    }

    private suspend fun logout(){
        viewModel.logout()
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), preferences)
}