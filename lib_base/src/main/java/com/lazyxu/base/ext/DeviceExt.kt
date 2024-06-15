package com.lazyxu.base.ext

import android.content.res.Resources
import android.util.TypedValue

val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this, Resources.getSystem().displayMetrics
    )

val Int.dp2pxFloat
    get() = this.toFloat().dp2px



val Float.dp2pxInt
    get() = (this.dp2px + 0.5f).toInt()

val Int.dp2px
    get() = this.toFloat().dp2pxInt

