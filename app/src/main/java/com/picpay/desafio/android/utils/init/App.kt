package com.picpay.desafio.android.utils.init

import android.app.Application
import com.picpay.desafio.android.userList.model.di.localRepositoryModule
import com.picpay.desafio.android.userList.model.di.remoteRepositoryModule
import com.picpay.desafio.android.userList.model.di.viewModuleModule
import com.picpay.desafio.android.utils.di.database
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("UNUSED")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(viewModuleModule, localRepositoryModule, remoteRepositoryModule, database)
        }
    }
}
