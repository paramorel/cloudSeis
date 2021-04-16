package com.example.cloudseis.network

import android.util.Log
import com.example.cloudseis.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "http://84.237.89.72:8888/"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                        }.build())
                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            Log.i("FROM REMOTE DATA ", "zapros");
                            val logging = HttpLoggingInterceptor()
                            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
                            client.addInterceptor(logging)
                        }
                    }
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
