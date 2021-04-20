package com.picpay.desafio.android.userList.model

import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): Response<List<Contact>>
}
