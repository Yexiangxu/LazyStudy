package com.lazyxu.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.ext.dp2pxFloat
import com.lazyxu.base.ext.sp2pxFloat
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.function.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * 仪表盘
 */
class DashboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var startAngle: Float
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
    }
    private var mTxtPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
    }
    private var pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 4.dp2pxFloat
        color = Color.RED
    }
    private var radius = 0f
    private var pointerLength = 0f


    private var longTickLength: Float
    private var shortTickLength: Float
    private var longTickWidth: Float
    private var shortTickWidth: Float
    private var arcWidth = 4.dp2pxFloat//仪表盘圈的宽度
    private var currentValue = 0

    /**
     * 仪表盘扫过的弧度
     */
    private val sweepAngle = 270f

    init {
        // 获取自定义属性
        context.obtainStyledAttributes(
            attrs,
            R.styleable.DashboardView
        ).apply {
            try {
                longTickLength =
                    getDimension(R.styleable.DashboardView_longTickLength, 20.dp2pxFloat)
                shortTickLength =
                    getDimension(R.styleable.DashboardView_shortTickLength, 10.dp2pxFloat)
                longTickWidth = getDimension(R.styleable.DashboardView_longTickWidth, 4.dp2pxFloat)
                shortTickWidth =
                    getDimension(R.styleable.DashboardView_shortTickWidth, 2.dp2pxFloat)
            } finally {
                recycle()
            }
        }
        startAngle = 90f + (360 - sweepAngle) / 2
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min(w, h) / 2.toFloat()
        pointerLength = radius * 0.6f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        LogUtils.d(LogTag.CUSTOMVIEW, "onDraw")
        drawArc(canvas)
        drawTicks(canvas)
        drawText(canvas)
        drawPointer(canvas)
        drawCurrentValue(canvas)
    }


    private fun drawArc(canvas: Canvas) {
        mPaint.apply {
            strokeWidth = arcWidth
            color = Color.BLACK
        }
        canvas.drawArc(
            width / 2 + mPaint.strokeWidth / 2 - radius,
            height / 2 + mPaint.strokeWidth / 2 - radius,
            width / 2 - mPaint.strokeWidth / 2 + radius,
            height / 2 - mPaint.strokeWidth / 2 + radius,
            startAngle,
            sweepAngle,
            false,
            mPaint
        )
    }

    /**
     * 画刻度
     * 方式一：计算每个刻度的起始点和落脚点坐标
     */
    private fun drawTicks(canvas: Canvas) {
        mPaint.color = Color.BLACK
        val totalTicks = 60
        val angleStep = sweepAngle / totalTicks
        for (i in 0..totalTicks) {
            val isLongTick = i % 5 == 0
            val tickLength = if (isLongTick) longTickLength else shortTickLength
            val tickWidth = if (isLongTick) longTickWidth else shortTickWidth
            mPaint.strokeWidth = tickWidth
            val angle = Math.toRadians(startAngle.toDouble() + i * angleStep)
            val startX = (width / 2 + (radius - arcWidth - tickLength) * cos(angle)).toFloat()
            val startY = (height / 2 + (radius - arcWidth - tickLength) * sin(angle)).toFloat()
            val stopX = (width / 2 + radius * cos(angle)).toFloat()
            val stopY = (height / 2 + radius * sin(angle)).toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, mPaint)
        }
    }

    private fun drawCurrentValue(canvas: Canvas) {
        val valueX = (width / 2).toFloat()
        val valueY = (height / 2 + 40.dp2pxFloat).toFloat()
        mTxtPaint.apply {
            color = Color.RED
            textSize = 30.sp2pxFloat
        }
        canvas.drawText(
            "$animatedValue",
            valueX,
            valueY - (mTxtPaint.descent() + mTxtPaint.ascent()) / 2,
            mTxtPaint
        )
    }

    private fun drawText(canvas: Canvas) {
        mTxtPaint.apply {
            color = Color.RED
            textSize = 16.sp2pxFloat
        }
        val totalTicks = 13  // 大刻度数量
        val angleStep = sweepAngle / (totalTicks - 1)
        for (i in 0 until totalTicks) {
            val angle = Math.toRadians(startAngle.toDouble() + i * angleStep)
            val label = (i * 5).toString()  // 假设每个大刻度间隔5
            // 计算文本的位置，使其位于刻度的下方
            val textRadius = radius - arcWidth - longTickLength - 20.dp2px
            val x = (width / 2 + textRadius * cos(angle)).toFloat()
            val y = (height / 2 + textRadius * sin(angle)).toFloat()
            // 测量文本的宽度和高度
//            val textWidth = mTxtPaint.measureText(label)
            val textHeight = mTxtPaint.fontMetrics.descent - mTxtPaint.fontMetrics.ascent
            // 绘制文本，使其中心对齐刻度线
            canvas.drawText(label, x, y + textHeight / 2, mTxtPaint)
        }
    }

    /**
     * 画指针
     * 方式一：计算落脚点坐标
     */
    private fun drawPointer(canvas: Canvas) {
        val angle = Math.toRadians(startAngle.toDouble() + animatedValue * sweepAngle / 60) // 度数转弧度
        val pointerX = (width / 2 + pointerLength * cos(angle)).toFloat()
        val pointerY = (height / 2 + pointerLength * sin(angle)).toFloat()
        canvas.drawLine(width / 2f, height / 2f, pointerX, pointerY, pointPaint)
    }

    /**
     * 画指针
     * 方式二：旋转
     */
//    private fun drawPointer(canvas: Canvas) {
//        canvas.save()//有旋转操作，保存操作前画布，旋转结束恢复到操作前
//        canvas.rotate((startAngle + animatedValue * sweepAngle / 60), width / 2f, height / 2f)
//        canvas.drawLine(
//            width / 2f,
//            height / 2f,
//            width / 2f + pointerLength,
//            height / 2f,
//            pointPaint
//        )
//        canvas.restore()
//    }

    fun setCurrentValue(value: Int) {
        animatePointer(currentValue, value)
        currentValue = value
    }

    private var animatedValue = 0
    private fun animatePointer(fromValue: Int, toValue: Int) {
        val animator = ValueAnimator.ofInt(fromValue, toValue)
        animator.duration = 500 // 动画时长为500毫秒
        animator.addUpdateListener { animation ->
            animatedValue = animation.animatedValue as Int
            invalidate() // 触发视图重绘
        }
        animator.start()
    }
}