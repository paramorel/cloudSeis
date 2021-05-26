package com.example.cloudseis.presentation.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.PrivateAndPublicNetworksResponse
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.example.cloudseis.network.Answer
import com.example.cloudseis.presentation.ui.bases.BaseViewModel
import kotlinx.coroutines.launch

class RegistrarsViewModel(
    private val repository: RegistrarsRepository,
) : BaseViewModel(repository) {

    private val _registrars: MutableLiveData<Answer<List<RegistrarByIdResponse>>> = MutableLiveData()
    val registrars: MutableLiveData<Answer<List<RegistrarByIdResponse>>>
        get() = _registrars

    private val _networks: MutableLiveData<Answer<PrivateAndPublicNetworksResponse>> = MutableLiveData()
    val networks: MutableLiveData<Answer<PrivateAndPublicNetworksResponse>>
        get() = _networks

    fun getPrivateAndPublicNetworks(token: String) = viewModelScope.launch {
        _networks.value = Answer.Loading
        _networks.value = repository.getPublicAndPrivateNetworks(token)
        Log.i("FROM REG VIEWMODEL", "get private and pub networks")

    }

    fun getRegistrarsByNetworkI(networkId : Long) = viewModelScope.launch {
        //Log.i("FROM REG VIEWMODEL", "gerReg")
        _registrars.value = Answer.Loading
        _registrars.value = repository.registrarsByNetworkId(networkId)
    }

}