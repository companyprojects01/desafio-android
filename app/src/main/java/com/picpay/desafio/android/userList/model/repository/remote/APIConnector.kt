package com.picpay.desafio.android.userList.model.repository.remote

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIConnector {

    const val url = "http://careers.picpay.com/tests/mobdev/"

    inline fun <reified T> provideAPIInstance(baseUrl: String = url): T {

        val logging = HttpLoggingInterceptor {
                message -> Log.d("OkHttp", message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(T::class.java)
    }
}
