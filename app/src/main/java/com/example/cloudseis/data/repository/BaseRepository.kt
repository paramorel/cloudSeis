package com.example.cloudseis.data.repository

import com.example.cloudseis.network.Answer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Answer<T> {
        return withContext(Dispatchers.IO) {
            try {
                Answer.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Answer.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Answer.Failure(true, null, null)
                    }
                }
            }
        }
    }

}
