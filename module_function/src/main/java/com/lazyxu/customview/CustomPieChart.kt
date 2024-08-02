package com.lazyxu.customview


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

class CustomPieChart(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 设置反锯齿
        paint.isAntiAlias = true

        // 饼图的参数
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = 200f
        val gap = 20f // 偏移距离

        // 定义绘制区域
        val oval = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        // 绘制第一个部分 (70%)
        paint.color = Color.BLUE
        canvas.drawArc(oval, 135f, 252f, true, paint)

        // 第二部分 (30%)的起始角度
        val startAngle = 135f + 252f // 第二部分的起始角度
        val sweepAngle = 108f // 第二部分的角度

        // 绘制第二部分的区域
        paint.color = Color.RED

        // 计算间隙的位置
        val gapStartAngle = startAngle + sweepAngle / 2 // 第二部分的中心角度
        val gapRad = toRadians(gapStartAngle.toDouble())
        val offsetX = gap * cos(gapRad).toFloat()
        val offsetY = gap * sin(gapRad).toFloat()

        // 计算偏移后的绘制区域
        val oval2 = RectF(
            centerX - radius + offsetX, centerY - radius + offsetY,
            centerX + radius + offsetX, centerY + radius + offsetY
        )

        // 绘制第二部分 (30%)
        canvas.drawArc(oval2, startAngle, sweepAngle, true, paint)
    }
}
