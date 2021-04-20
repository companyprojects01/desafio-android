package com.picpay.desafio.android.userList.model.repository.remote

import com.picpay.desafio.android.userList.model.PicPayService
import org.junit.Test

class APIConnectorTest {

    @Suppress("SENSELESS_COMPARISON")
    @Test
    fun `check if method returns an instance of the specified type`() {
        //Given
        val service: Any
        //When
        service = APIConnector.provideAPIInstance<PicPayService>()
        ///Then
        assert(service is PicPayService)
    }
}
