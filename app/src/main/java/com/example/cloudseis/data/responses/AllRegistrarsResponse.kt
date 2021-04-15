package com.example.cloudseis.data.responses

import com.google.gson.annotations.SerializedName

data class AllRegistrarsResponse(val name: String,
        val channelsCount: String,
        val gpsN: String,
        val gpsW: String,
        val haveConnection: String,
        val timeout: String,
        val type: String,
        val networkId: String)