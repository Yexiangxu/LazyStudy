package com.lazyxu.function.animation.typeevaluator

import android.animation.TypeEvaluator
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils

class ColorEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        LogUtils.d(LogTag.ANIMATION,"fraction=$fraction,startValue=$startValue,endValue=$endValue")
        val startA = (startValue shr 24) and 0xff
        val startR = (startValue shr 16) and 0xff
        val startG = (startValue shr 8) and 0xff
        val startB = startValue and 0xff

        val endA = (endValue shr 24) and 0xff
        val endR = (endValue shr 16) and 0xff
        val endG = (endValue shr 8) and 0xff
        val endB = endValue and 0xff

        val currentA = (startA + (fraction * (endA - startA)).toInt()) shl 24
        val currentR = (startR + (fraction * (endR - startR)).toInt()) shl 16
        val currentG = (startG + (fraction * (endG - startG)).toInt()) shl 8
        val currentB = startB + (fraction * (endB - startB)).toInt()

        return currentA or currentR or currentG or currentB
    }
}
