package com.picpay.desafio.android.userList.model.repository.local

import com.picpay.desafio.android.userList.model.Contact
import com.picpay.desafio.android.userList.model.repository.local.db.Database

internal class LocalRepository(private val database: Database) {

    suspend fun fetchCachedContacts(): List<Contact> {
        return database.contactsDao().getAllContacts()
    }

    suspend fun saveAllContacts(contacts: List<Contact>) {
        database.contactsDao().saveAllContacts(contacts)
    }

    suspend fun dropContactsTable(contacts: List<Contact>) {
        database.contactsDao().dropContactsTable(contacts)
    }
}
