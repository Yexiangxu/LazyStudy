package com.lazyxu.base.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.lazyxu.base.R
import com.lazyxu.base.utils.transformation.CircleBorderTransform
import com.lazyxu.base.utils.transformation.CircleCropTransformation


fun ImageView.load(url: Any?, @DrawableRes placeholder: Int = R.drawable.default_image) {
    Glide.with(context.validContext)
        .asDrawable()//没有复杂操作使用drawable能更好的管理内存
        .load(url)
        .error(placeholder)
        .placeholder(placeholder)
        .transform(CenterCrop())
        .skipMemoryCache(false) //启用内存缓存
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //磁盘缓存策略
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
        .skipMemoryCache(false) //启用内存缓存
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //磁盘缓存策略
        .into(this)
}

/**
 * 加载圆形图
 */
fun ImageView.loadCircle(
    url: Any?,
    placeholder: Int = R.drawable.default_image,
) {
    Glide.with(context.validContext)
//        .asDrawable()//没有复杂操作使用drawable能更好的管理内存,需要用gif图注释掉
        .load(url)
        .transform(CircleCropTransformation(), MultiTransformation(CenterCrop()))
        .error(placeholder).placeholder(placeholder)
        .skipMemoryCache(false) //启用内存缓存
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //磁盘缓存策略
        .into(this)
}

/**
 * 加载圆形边框图
 */
fun ImageView.loadCircleBorder(
    url: Any?,
    borderWidth: Float, borderColor: Int,
    placeholder: Int = R.drawable.default_image,
) {
    Glide.with(context.validContext)
        .load(url)
        .error(context.validContext.drawable(placeholder))
        .placeholder(context.validContext.drawable(placeholder))
        .transform(CircleBorderTransform(borderWidth, context.validContext.color(borderColor)))
        .skipMemoryCache(false) //启用内存缓存
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //磁盘缓存策略，只缓存转换后的资源
        .into(this)
}