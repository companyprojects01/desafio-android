package com.picpay.desafio.android.utils.di

import androidx.room.Room
import com.picpay.desafio.android.userList.model.repository.local.db.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val database = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "database"
        ).build()
    }
}
