package com.example.cloudseis.network

import okhttp3.ResponseBody
import retrofit2.http.POST

interface UserApi {
    @POST("logout")
    suspend fun logout(): ResponseBody

}