package com.example.cloudseis.presentation.ui.registrars

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.databinding.FragmentMapBinding
import com.example.cloudseis.databinding.FragmentRegistrarsBinding
import com.example.cloudseis.network.RegistrarsApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.map.RegistrarsViewModel

class RegistrarsFragment : BaseFragment<RegistrarsViewModel, FragmentRegistrarsBinding, RegistrarsRepository>(){
    override fun getViewModel() = RegistrarsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrarsBinding = FragmentRegistrarsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        RegistrarsRepository(remoteDataSource.buildApi(RegistrarsApi::class.java), userPreferences)
}