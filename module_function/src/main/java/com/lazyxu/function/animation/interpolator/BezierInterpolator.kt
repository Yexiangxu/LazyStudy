package com.lazyxu.function.animation.interpolator

import android.graphics.PointF
import android.view.animation.Interpolator
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils

/**
 * 贝塞尔曲线
 */
class BezierInterpolator(x1: Float, y1: Float, x2: Float, y2: Float) : Interpolator {

    private companion object {
        const val ACCURACY = 4096
    }

    private var lastI = 0
    private val controlPoint1 = PointF(x1, y1)
    private val controlPoint2 = PointF(x2, y2)

    override fun getInterpolation(input: Float): Float {
        LogUtils.d(LogTag.ANIMATION,"input=$input")
        var t = input
        // 近似求解t的值[0,1]
        for (i in lastI until ACCURACY) {
            t = i.toFloat() / ACCURACY
            val x = cubicCurves(
                t.toDouble(),
                0.0,
                controlPoint1.x.toDouble(),
                controlPoint2.x.toDouble(),
                1.0
            )
            if (x >= input) {
                lastI = i
                break
            }
        }

        var value = cubicCurves(
            t.toDouble(),
            0.0,
            controlPoint1.y.toDouble(),
            controlPoint2.y.toDouble(),
            1.0
        )
        if (value > 0.999) {
            value = 1.0
            lastI = 0
        }
        return value.toFloat()
    }

    /**
     * 求三次贝塞尔曲线(四个控制点)一个点某个维度的值.
     * 参考资料: <em> http://devmag.org.za/2011/04/05/bzier-curves-a-tutorial/ </em>
     *
     * @param t      取值[0, 1]
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     * @return
     */
    private fun cubicCurves(
        t: Double,
        value0: Double,
        value1: Double,
        value2: Double,
        value3: Double
    ): Double {
        val u = 1 - t
        val tt = t * t
        val uu = u * u
        val uuu = uu * u
        val ttt = tt * t
        return (uuu * value0 +
                3 * uu * t * value1 +
                3 * u * tt * value2 +
                ttt * value3)
    }
}