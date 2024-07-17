package com.lazyxu.base.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.lazyxu.base.R


fun ImageView.load(url: Any?, @DrawableRes placeholder: Int = R.drawable.default_image) {
    Glide.with(context.validContext)
        .load(url)
        .error(placeholder)
        .placeholder(placeholder)
        .transform(CenterCrop())
        .into(this)
}


/**
 * 加载横向圆角矩形图片
 */
fun ImageView.loadRound(
    url: Any?,
    radius: Int = 5,
    @DrawableRes placeholder: Int = R.drawable.default_image
) {
    Glide.with(context.validContext)
        .load(url)
        .error(placeholder)
        .placeholder(placeholder)
        .transform(CenterCrop(), RoundedCorners(radius.dp2px))
        .into(this)
}

/**
 * 加载圆形图
 */
fun ImageView.loadCircle(
    url: Any?,
    @DrawableRes placeholder: Int = R.drawable.default_image,
) {
    Glide.with(context.validContext)
        .load(url)
        .error(placeholder).placeholder(placeholder)
        .transform(CenterCrop(), MultiTransformation(CenterCrop()))
        .into(this)
}
