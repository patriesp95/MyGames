package com.patriciamespert.mygamesac.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?){
    visibility = if(visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null)  Glide.with(context).load(url).into(this)
}