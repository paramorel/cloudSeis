package com.example.cloudseis.network

import okhttp3.ResponseBody

sealed class Answer<out T> {
    data class Success<out T>(val value: T) : Answer<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Answer<Nothing>()
    object Loading : Answer<Nothing>()
}
