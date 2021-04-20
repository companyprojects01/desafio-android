package com.picpay.desafio.android.userList.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Contact(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @ColumnInfo
    @SerializedName("img") val img: String,
    @ColumnInfo
    @SerializedName("name") val name: String,
    @ColumnInfo
    @SerializedName("username") val username: String
)
