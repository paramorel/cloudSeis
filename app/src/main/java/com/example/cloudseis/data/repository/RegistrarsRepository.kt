package com.example.cloudseis.data.repository

import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.network.AuthApi
import com.example.cloudseis.network.RegistrarsApi

class RegistrarsRepository (
    private val api: RegistrarsApi,
    private val preferences: UserPreferences
    ) : BaseRepository(){


    suspend fun registrarsByNetworkId(networkId: Long) = safeApiCall {
        api.registrarsByNetworkId(networkId)
    }
}