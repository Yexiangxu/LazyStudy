package com.lazyxu.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.ext.dp2pxFloat
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils

class DashBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var pathEffect: PathDashPathEffect
    private val sweepAngle = 270f

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)//Paint.ANTI_ALIAS_FLAG 抗锯齿标志
    private val DASH_WIDTH = 2f.dp2px
    private val DASH_HEIGHT = 5f.dp2px
    private val path = Path().apply {

    }
    private val dash = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
    }
    private val tickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 4f.dp2px
        style = Paint.Style.STROKE
    }

    private var dashCount = 20//设置20个刻度

    init {
        mPaint.strokeWidth = 4f.dp2px
        mPaint.style = Paint.Style.STROKE
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
        //pathEffect不是加特效，而是用特效形式来画
        pathEffect = PathDashPathEffect(
            dash,
            (pathMeasure.length - DASH_WIDTH) / dashCount,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
        tickPaint.pathEffect = pathEffect//advance从哪里开始，phase间隔多少
    }

    private val DASH_LENGTH = 120f.dp2px

    override fun draw(canvas: Canvas) {
        //draw会多次执行，eg：点击home键再返回会执行该方法，所以一般不在draw里面执行耗时操作
        super.draw(canvas)
        LogUtils.d(LogTag.CUSTOMVIEW, "draw执行")
        canvas.drawPath(
            path,
            mPaint
        )
        //弧度
        canvas.drawPath(
            path,//false起点和终点连接起来不展示中心点
            tickPaint
        )
        //弧度
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + DASH_LENGTH * Math.cos(20.toDouble()).toFloat(),
            height / 2f + DASH_LENGTH * Math.sin(20.toDouble()).toFloat(),
            mPaint
        )
    }

}
