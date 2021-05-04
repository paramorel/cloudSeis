package com.example.cloudseis.data.repository

import android.util.Log
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.data.json.LoginInfo
import com.example.cloudseis.data.json.RegistrationInfo
import com.example.cloudseis.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        loginInfo: LoginInfo
    ) = safeApiCall {
        api.login(loginInfo)
    }


    suspend fun register(
        registerInfo: RegistrationInfo
    ) = safeApiCall {
        api.register(registerInfo)
    }


    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun logout(){
        if (preferences.authToken != null){
            preferences.logout()
            Log.i("AuthRepository", "smth happens")
        } else {
            Log.i("AuthRepository", "nothing happens")
        }
    }
}
