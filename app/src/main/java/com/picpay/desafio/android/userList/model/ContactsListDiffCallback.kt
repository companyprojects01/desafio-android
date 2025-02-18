package com.picpay.desafio.android.userList.model

import androidx.recyclerview.widget.DiffUtil

class ContactsListDiffCallback(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}