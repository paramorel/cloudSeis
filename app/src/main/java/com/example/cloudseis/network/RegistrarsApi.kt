package com.example.cloudseis.network

import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.responses.AllNetworksResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrarsApi {
    @POST("networks/all")
    suspend fun allNetworks() : AllNetworksResponse;

}