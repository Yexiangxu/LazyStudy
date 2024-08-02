package com.lazyxu.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2pxFloat
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils

/**
 * 自定义view
 */
class CustomView1 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        LogUtils.d(LogTag.CUSTOMVIEW,"onDraw")
        mPaint.setColor(Color.RED)
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 5.dp2pxFloat//宽度在起始点上下各分配一半
        canvas.drawLine(50.dp2pxFloat, 50.dp2pxFloat, 100.dp2pxFloat, 50.dp2pxFloat, mPaint)

        mPaint.setColor(Color.BLUE)
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas.drawLine(50.dp2pxFloat, 0f, 100.dp2pxFloat, 0f, mPaint)
        mPaint.setColor(Color.BLUE)
        canvas.drawCircle(50.dp2pxFloat, 100.dp2pxFloat, 50.dp2pxFloat, mPaint)
    }

}
