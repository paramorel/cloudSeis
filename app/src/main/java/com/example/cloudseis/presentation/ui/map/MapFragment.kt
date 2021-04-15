package com.example.cloudseis.presentation.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.databinding.FragmentMapBinding
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.network.RegistrarsApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.login.AuthViewModel

class MapFragment : BaseFragment<RegistrarsViewModel, FragmentMapBinding, RegistrarsRepository>(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //////
    }

    override fun getViewModel() = RegistrarsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapBinding = FragmentMapBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        RegistrarsRepository(remoteDataSource.buildApi(RegistrarsApi::class.java), userPreferences)
}