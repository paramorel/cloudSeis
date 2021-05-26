package com.example.cloudseis.data.repository

import com.example.cloudseis.data.Preferences
import com.example.cloudseis.network.StationsApi

class StationRepository (
    private val api: StationsApi,
    private val preferences: Preferences
) : BaseRepository(){



    suspend fun registrarsByNetworkId(networkId: Long) = safeApiCall {
        api.registrarsByNetworkId(networkId)
    }

    suspend fun getPublicAndPrivateNetworks(token: String) = safeApiCall {
        api.privateAndPublicNetworks(token)

    }
}