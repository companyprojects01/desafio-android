package com.picpay.desafio.android.userList.model.repository.remote

import com.picpay.desafio.android.userList.model.PicPayService
import com.picpay.desafio.android.userList.model.states.StatesAPI
import java.io.IOException

internal class RemoteRepository(private val service: PicPayService) {

    suspend fun getUserList(): StatesAPI {
        try {
            with(service.getUsers()) {
                if (isSuccessful) {
                    body()?.let {
                        return StatesAPI.Success(it)
                    }
                } else {
                    return if (code() in 400..499) {
                        StatesAPI.Error(StatesAPI.ClientErrorViewState)
                    } else {
                        StatesAPI.Error(StatesAPI.ServerErrorViewState)
                    }
                }
            }
        } catch (exception: IOException) {
            return StatesAPI.Error(StatesAPI.Exception)
        }
        return StatesAPI.Error(StatesAPI.Generic())
    }
}
