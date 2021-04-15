package com.example.cloudseis.data.json

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String)

