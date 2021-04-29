package com.example.cloudseis.presentation.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.PrivateAndPublicNetworksResponse
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

    private val _networks: MutableLiveData<Resource<PrivateAndPublicNetworksResponse>> = MutableLiveData()
    val networks: MutableLiveData<Resource<PrivateAndPublicNetworksResponse>>
        get() = _networks

    fun getPrivateAndPublicNetworks(token: String) = viewModelScope.launch {
        Log.i("FROM REG VIEWMODEL", "get private and pub networks")
        _networks.value = Resource.Loading
        _networks.value = repository.getPublicAndPrivateNetworks(token)
    }

    fun getRegistrarsByNetworkI(networkId : Long) = viewModelScope.launch {
        Log.i("FROM REG VIEWMODEL", "gerReg")
        _registrars.value = Resource.Loading
        _registrars.value = repository.registrarsByNetworkId(networkId)
    }

}