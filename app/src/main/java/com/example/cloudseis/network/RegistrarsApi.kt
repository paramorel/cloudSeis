package com.example.cloudseis.network

import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.responses.AllNetworksResponse
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RegistrarsApi {
    @POST("networks/all")
    suspend fun allNetworks() : AllNetworksResponse;

    @GET("registrars/network/{id}" )
    suspend fun registrarsByNetworkId(
        @Path("id") networkId: Long
    ) : List<RegistrarByIdResponse>;

}