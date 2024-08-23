package com.lazyxu.function.animation.typeevaluator

import android.animation.TypeEvaluator
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import kotlin.math.exp

class ExponentialDecayEvaluator : TypeEvaluator<Float> {
    override fun evaluate(fraction: Float, startValue: Float, endValue: Float): Float {
        val decayFactor = 5.0
        val deltaValue = endValue - startValue
        val currentValue = endValue - deltaValue * exp(-decayFactor * fraction).toFloat()
        LogUtils.d(
            LogTag.ANIMATION,
            "currentValue=$currentValue,fraction=$fraction,startValue=$startValue,endValue=$endValue"
        )
        return currentValue
    }
}