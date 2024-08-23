package com.lazyxu.function.animation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2pxFloat

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var circleColor = Color.RED
    var radius = 50.dp2pxFloat
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        circlePaint.color = circleColor
        canvas.drawCircle(100.dp2pxFloat, 150.dp2pxFloat, radius, circlePaint)
    }

    fun setBackGroudColor(color: Int) {
        circleColor = color
        invalidate()
    }
}