package com.example.cloudseis.data.json

import com.google.gson.annotations.SerializedName

data class RegistrationInfo(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("organization") val organization: String)