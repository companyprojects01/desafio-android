package com.picpay.desafio.android.userList.model.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.userList.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}
