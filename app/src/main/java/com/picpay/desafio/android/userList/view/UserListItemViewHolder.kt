package com.picpay.desafio.android.userList.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.userList.model.Contact
import com.picpay.desafio.android.utils.extensions.loadImage
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(contact: Contact) {
        itemView.name.text = contact.name
        itemView.username.text = contact.username
        itemView.progressBar.visibility = View.VISIBLE
        itemView.contactImage.loadImage(contact.img,
            R.drawable.ic_round_account_circle,
            onSuccess = {
                itemView.progressBar.visibility = View.GONE
            },
            onError = {
                itemView.progressBar.visibility = View.GONE
            }
        )
    }
}
