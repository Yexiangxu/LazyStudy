package com.lazyxu.lazystudy.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.lazyxu.base.ext.dp2pxFloat

class TestView : View {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)//Paint.ANTI_ALIAS_FLAG 抗锯齿标志
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        Log.d("TesttViewTag", "draw执行")
        paint.isAntiAlias = true
        paint.strokeWidth=10.dp2pxFloat
        canvas.drawLine(100.dp2pxFloat, 100.dp2pxFloat, 200.dp2pxFloat, 200.dp2pxFloat, paint)
//        canvas.drawCircle(width / 2f, width / 2f, 150.dp2pxFloat, paint)
        canvas.drawPath(path, paint)

    }
    private lateinit var pathMeasure:PathMeasure

    private val path = Path()
    private val RADIUS = 100.dp2pxFloat
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("TesttViewTag", "onSizeChanged")
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)//CCW逆时针,CW顺时针
        path.addRect(
            width / 2f - RADIUS,
            height / 2f,
            width / 2f + RADIUS,
            height / 2f + 2 * RADIUS,
            Path.Direction.CCW
        )
        pathMeasure=PathMeasure(path,false)
//        pathMeasure.getPosTan()
        path.fillType = Path.FillType.EVEN_ODD
    }
}