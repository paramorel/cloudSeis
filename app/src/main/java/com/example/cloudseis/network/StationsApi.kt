package com.example.cloudseis.network

import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.data.responses.AllNetworksResponse
import com.example.cloudseis.data.responses.PrivateAndPublicNetworksResponse
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import retrofit2.Call
import retrofit2.http.*

interface StationsApi {
    @POST("networks/all")
    suspend fun allNetworks() : AllNetworksResponse;

    @GET("registrars/network/{id}" )
    suspend fun registrarsByNetworkId(
        @Path("id") networkId: Long
    ) : List<RegistrarByIdResponse>;

    @GET("/networks/all/available?")
    suspend fun privateAndPublicNetworks(
        @Header("Authorization") token : String
    ) : PrivateAndPublicNetworksResponse
}