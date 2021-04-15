package com.example.cloudseis.data.repository

import com.example.cloudseis.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

}