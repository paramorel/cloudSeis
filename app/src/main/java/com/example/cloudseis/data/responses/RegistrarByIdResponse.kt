package com.example.cloudseis.data.responses

import com.google.gson.annotations.SerializedName

data class RegistrarByIdResponse(val id: Int,
                                 val name: String,
                                 val channelsCount: String,
                                 val gpsN: String,
                                 val gpsW: String,
                                 val haveConnection: String,
                                 val lastConnection: String,
                                 val timeout: String,
                                 val type: String,
                                 val networkId: String)