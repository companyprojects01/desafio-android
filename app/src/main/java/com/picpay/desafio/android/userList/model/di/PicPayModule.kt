package com.picpay.desafio.android.userList.model.di

import com.picpay.desafio.android.userList.model.repository.local.LocalRepository
import com.picpay.desafio.android.userList.model.repository.remote.APIConnector
import com.picpay.desafio.android.userList.model.repository.remote.RemoteRepository
import com.picpay.desafio.android.userList.viewModel.PicPayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { PicPayViewModel(get(), get()) }
}

val localRepositoryModule = module {
    single { LocalRepository(get()) }
}

val remoteRepositoryModule = module {
    factory { RemoteRepository(APIConnector.provideAPIInstance()) }
}
