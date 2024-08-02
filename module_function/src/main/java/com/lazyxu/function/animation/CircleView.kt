package com.lazyxu.function.animation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2pxFloat

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var radius = 50.dp2pxFloat
        set(value) {
            field = value
            invalidate()
        }
}