package com.example.cloudseis.data

data class Station (
    val id: String,
    val name: String,
    val channelsCount: String,
    val gpsN: String,
    val gpsW: String,
    val haveConnection: String,
    val lastConnection: String,
    val timeout: String,
    val type: String,
    val networkId: String,
    val ownerId: String
)
