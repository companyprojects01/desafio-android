package com.picpay.desafio.android.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(
    url: String,
    @DrawableRes failurePlaceholder: Int,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    Picasso
        .get()
        .load(url)
        .error(failurePlaceholder)
        .into(this, object : Callback {
            override fun onSuccess() {
                onSuccess()
            }

            override fun onError(e: Exception?) {
                onError()
            }
        })
}
