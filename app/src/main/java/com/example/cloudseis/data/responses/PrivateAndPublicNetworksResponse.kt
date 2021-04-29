package com.example.cloudseis.data.responses

data class PrivateAndPublicNetworksResponse (
    val publicNetworks : Array<AllNetworksResponse>,
    val privateNetworks : Array<AllNetworksResponse>
)
