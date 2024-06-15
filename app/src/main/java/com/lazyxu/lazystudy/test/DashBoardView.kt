package com.lazyxu.lazystudy.test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.ext.dp2pxFloat

class DashBoardView : View {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val sweepAngle = 220f

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)//Paint.ANTI_ALIAS_FLAG 抗锯齿标志
    private val DASH_WIDTH = 2f.dp2px
    private val DASH_HEIGHT = 5f.dp2px
    private val path = Path()
    private val dash = Path()
    private lateinit var pathEffect: PathDashPathEffect

    init {
        mPaint.strokeWidth = 4f.dp2px
        mPaint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - 150.dp2pxFloat,
            height / 2f - 150.dp2pxFloat,
            width / 2f + 150.dp2pxFloat,
            height / 2f + 150.dp2pxFloat,
            90f + (360 - sweepAngle) / 2,
            sweepAngle
        )
        val pathMeasure = PathMeasure(path, false)
        pathMeasure.length / 20f
        pathEffect = PathDashPathEffect(
            dash,
            (pathMeasure.length - DASH_WIDTH) / 20f,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    private val DASH_LENGTH = 120f.dp2px
    override fun draw(canvas: Canvas) {
        //draw会多次执行，eg：点击home键再返回会执行该方法，所以一般不在draw里面执行耗时操作
        super.draw(canvas)
        Log.d("TesttViewTag", "draw执行")
        canvas.drawPath(
            path,
//            false,//false起点和终点连接起来不展示中心点
            mPaint
        )//弧度
        mPaint.pathEffect = pathEffect//advance从哪里开始，phase间隔多少
        canvas.drawPath(
            path,//false起点和终点连接起来不展示中心点
            mPaint
        )//弧度
        canvas.drawLine(
            width / 2f,
            height / 2f,
            DASH_LENGTH * Math.cos(20.toDouble()).toFloat(),
            DASH_LENGTH * Math.sin(20.toDouble()).toFloat(),
            mPaint
        )
        mPaint.reset()
    }


}