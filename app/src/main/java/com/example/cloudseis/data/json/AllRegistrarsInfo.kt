package com.example.cloudseis.data.json

import com.google.gson.annotations.SerializedName

data class AllRegistrarsInfo(
    @SerializedName("name") val name: String,
    @SerializedName("channelsCount") val channelsCount: String,
    @SerializedName("gpsN") val gpsN: String,
    @SerializedName("gpsW") val gpsW: String,
    @SerializedName("haveConnection") val haveConnection: String,
    @SerializedName("timeout") val timeout: String,
    @SerializedName("type") val type: String,
    @SerializedName("networkId") val networkId: String)