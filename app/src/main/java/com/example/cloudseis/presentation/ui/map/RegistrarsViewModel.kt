package com.example.cloudseis.presentation.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.example.cloudseis.network.Resource
import com.example.cloudseis.presentation.ui.bases.BaseViewModel
import kotlinx.coroutines.launch

class RegistrarsViewModel(
    private val repository: RegistrarsRepository,
) : BaseViewModel(repository) {

    private val _registrars: MutableLiveData<Resource<List<RegistrarByIdResponse>>> = MutableLiveData()
    val registrars: MutableLiveData<Resource<List<RegistrarByIdResponse>>>
        get() = _registrars

    fun getRegistrarsByNetworkI(networkId : Long) = viewModelScope.launch {
        Log.i("FROM REG VIEWMODEL", "gerReg")
        _registrars.value = Resource.Loading
        _registrars.value = repository.registrarsByNetworkId(networkId)
    }

}