package com.example.cloudseis.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.data.responses.LoginResponse
import com.example.cloudseis.data.responses.RegisterResponce
import com.example.cloudseis.network.Answer
import com.example.cloudseis.presentation.ui.bases.BaseViewModel


class AuthViewModel(
    private val repository: AuthRepository,
) : BaseViewModel(repository) {


    private val _loginResponse: MutableLiveData<Answer<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Answer<LoginResponse>>
        get() = _loginResponse


    private val _registerResponse: MutableLiveData<Answer<RegisterResponce>> = MutableLiveData()
    val registerResponse: LiveData<Answer<RegisterResponce>>
        get() = _registerResponse

    fun login(loginInfo: LoginInfo) = viewModelScope.launch {
        _loginResponse.value = Answer.Loading
        _loginResponse.value = repository.login(loginInfo)
    }

    fun register(registerInfo: RegistrationInfo) = viewModelScope.launch {
        _registerResponse.value = Answer.Loading
        _registerResponse.value = repository.register(registerInfo)
    }
//
//    fun registerConfirm(registerConfirmInfo: RegisterConfirmInfo) = viewModelScope.launch {
//        _registerConfirmResponse.value = Resource.Loading
//        _registerConfirmResponse.value = repository.registerConfirm(registerConfirmInfo)
//    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

    suspend fun logout(){
        repository.logout()
    }
}
