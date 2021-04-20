package com.picpay.desafio.android.userList.model.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.userList.model.Contact

@Dao
interface ContactsDao {
    @Query("SELECT * FROM Contact")
    suspend fun getAllContacts(): List<Contact>

    @Insert
    suspend fun saveAllContacts(contacts: List<Contact>)

    @Delete(entity = Contact::class)
    suspend fun dropContactsTable(githubProfilesList: List<Contact>)
}
