package com.picpay.desafio.android

import com.picpay.desafio.android.userList.model.PicPayService
import com.picpay.desafio.android.userList.model.Contact
import kotlinx.coroutines.runBlocking

class ExampleService(
    private val service: PicPayService
) {

    fun example() {
        var list = listOf<Contact>()
        runBlocking {
            val users = service.getUsers().body()
            list = users ?: emptyList()
        }
    }
}