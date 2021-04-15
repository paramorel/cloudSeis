package com.example.cloudseis.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.data.responses.LoginResponse
import com.example.cloudseis.network.Resource
import com.example.cloudseis.presentation.ui.bases.BaseViewModel
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity


class AuthViewModel(
    private val repository: AuthRepository,
) : BaseViewModel(repository) {

/*
//    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
//    val loginResponse: LiveData<Resource<LoginResponse>>
//        get() = _loginResponse
*/
//
//    private val _registerResponse: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()
//    val registerResponse: LiveData<Resource<RegisterResponse>>
//        get() = _registerResponse
//

//    private val _registerConfirmResponse: MutableLiveData<Resource<RegisterConfirmResponse>> = MutableLiveData()
//    val registerConfirmResponse: LiveData<Resource<RegisterConfirmResponse>>
//        get() = _registerConfirmResponse


//    fun login(loginInfo: LoginInfo) = viewModelScope.launch {
//        _loginResponse.value = Resource.Loading
//        _loginResponse.value = repository.login(loginInfo)
//    }
//
//    fun register(registerInfo: RegisterInfo) = viewModelScope.launch {
//        _registerResponse.value = Resource.Loading
//        _registerResponse.value = repository.register(registerInfo)
//    }
//
//    fun registerConfirm(registerConfirmInfo: RegisterConfirmInfo) = viewModelScope.launch {
//        _registerConfirmResponse.value = Resource.Loading
//        _registerConfirmResponse.value = repository.registerConfirm(registerConfirmInfo)
//    }
}
