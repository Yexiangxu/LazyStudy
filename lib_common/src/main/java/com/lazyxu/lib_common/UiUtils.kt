package com.lazyxu.lib_common

import android.graphics.Color

object UiUtils {
    fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val startA = Color.alpha(startColor)
        val startR = Color.red(startColor)
        val startG = Color.green(startColor)
        val startB = Color.blue(startColor)
        val endA = Color.alpha(endColor)
        val endR = Color.red(endColor)
        val endG = Color.green(endColor)
        val endB = Color.blue(endColor)
        val alpha = (startA + (endA - startA) * fraction).toInt()
        val red = (startR + (endR - startR) * fraction).toInt()
        val green = (startG + (endG - startG) * fraction).toInt()
        val blue = (startB + (endB - startB) * fraction).toInt()
        return Color.argb(alpha, red, green, blue)
    }
}