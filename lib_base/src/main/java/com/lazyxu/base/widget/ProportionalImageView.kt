package com.lazyxu.base.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ProportionalImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val drawable = drawable
        if (drawable != null) {
            val screenWidth = MeasureSpec.getSize(widthMeasureSpec)
            val screenHeight = MeasureSpec.getSize(heightMeasureSpec)
            val imageWidth = drawable.intrinsicWidth
            val imageHeight = drawable.intrinsicHeight
            val imageAspectRatio = imageWidth.toFloat() / imageHeight
            val screenAspectRatio = screenWidth.toFloat() / screenHeight
            val width: Int
            val height: Int
            if (imageAspectRatio >= screenAspectRatio) {
                // 图片宽高比 >= 屏幕宽高比，宽撑满，高按比例
                width = screenWidth
                height = (screenWidth / imageAspectRatio).toInt()
            } else {
                // 图片宽高比 < 屏幕宽高比，高撑满，宽按比例
                height = screenHeight
                width = (screenHeight * imageAspectRatio).toInt()
            }
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
