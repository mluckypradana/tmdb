package com.luc.base.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luc.base.R
import com.luc.base.core.helper.JNIUtil


@BindingAdapter("srcUrl")
fun ImageView.srcUrl(url: String?) {
    loadResourceImage(JNIUtil.imageEndPoint() + url, R.drawable.bg_placeholder)
}

fun ImageView.loadResourceImage(url: String, placeholder: Int) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().placeholder(placeholder))
        .into(this)
}