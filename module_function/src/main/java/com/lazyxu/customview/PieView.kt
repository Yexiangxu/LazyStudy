package com.lazyxu.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2pxFloat
import com.lazyxu.base.ext.sp2pxFloat
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * 饼图
 */
class PieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var pieList = mutableListOf(
        PieEntity(value = 40f, color = Color.parseColor("#00b086")),
        PieEntity(value = 35f, color = Color.parseColor("#AC38ED")),
        PieEntity(value = 25f, color = Color.parseColor("#3780C0"))
    )
    private var pieRadius: Float = 0f
    private var startAngle = 135f
    private var gap = 4.dp2pxFloat
    private var piePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var txtPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        color = Color.BLACK
        textSize = 16.sp2pxFloat
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pieRadius = min(width, height) / 2 - gap
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        pieList.forEachIndexed { index, pieEntity ->
            piePaint.color = pieEntity.color
            val sweepAngle = 360 * pieEntity.value / 100
            canvas.save()
            val centerAngle = startAngle + sweepAngle / 2
            val gapRad = Math.toRadians(centerAngle.toDouble())
            val offsetX = gap * cos(gapRad).toFloat()
            val offsetY = gap * sin(gapRad).toFloat()
            canvas.drawArc(
                width / 2 - pieRadius + offsetX,
                height / 2 - pieRadius + offsetY,
                width / 2 + pieRadius + offsetX,
                height / 2 + pieRadius + offsetY,
                startAngle,
                sweepAngle,
                true,
                piePaint
            )
            canvas.drawText(
                "${pieEntity.value}%",
                (width / 2 + (pieRadius / 2) * cos(gapRad)).toFloat() + offsetX,
                (height / 2 + (pieRadius / 2) * sin(gapRad)).toFloat() + offsetY,
                txtPaint
            )
            canvas.restore()
            startAngle += sweepAngle
        }
    }

    fun setPieList(pieList: MutableList<PieEntity>) {
        this.pieList = pieList
        invalidate()
    }
}

data class PieEntity(val value: Float, val color: Int)