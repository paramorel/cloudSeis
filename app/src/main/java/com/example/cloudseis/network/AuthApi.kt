package com.example.cloudseis.network

import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.responses.LoginResponse
import com.example.cloudseis.data.responses.RegisterResponce
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {
    @POST("authenticate")
    suspend fun login(
        @Body loginInfo: LoginInfo
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body registerInfo: RegistrationInfo
    ): RegisterResponce
}