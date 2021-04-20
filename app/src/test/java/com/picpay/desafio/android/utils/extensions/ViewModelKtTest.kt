package com.picpay.desafio.android.utils.extensions

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.testUtils.TestCoroutineRule
import com.picpay.desafio.android.userList.model.repository.local.LocalRepository
import com.picpay.desafio.android.userList.model.repository.remote.RemoteRepository
import com.picpay.desafio.android.userList.viewModel.PicPayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ViewModelKtTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineRule = TestCoroutineRule()

    private val localRepository = LocalRepository(mock())
    private val remoteRepository = RemoteRepository(mock())
    private val viewModel = PicPayViewModel(localRepository, remoteRepository)

    @Test
    fun `check if task will be executed`() = runBlocking {
        //Given
        var index = 0
        //When
        viewModel.runTaskOnBackground {
            index = 1
        }
        //Then
        assertEquals(1, index)
    }
}