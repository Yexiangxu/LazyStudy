package com.lazyxu.test.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.ext.dp2pxFloat

class TestView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private  var paint=Paint(Paint.ANTI_ALIAS_FLAG)//开启抗锯齿（默认关闭，基本都要开启除了特殊需求）
    private val radius=50.dp2pxFloat

    /**
     * onDraw 中尽量避免反复创建paint
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.setColor(Color.RED)
        paint.strokeWidth=20.dp2pxFloat
        canvas?.drawLine(20.dp2pxFloat,20.dp2pxFloat,50.dp2pxFloat,50.dp2pxFloat,paint)
        paint.reset()
        canvas?.drawCircle(100.dp2pxFloat,50.dp2pxFloat,radius,paint)
        paint.isAntiAlias=true
        paint.style=Paint.Style.FILL//Paint.Style.FILL 填充、Paint.Style.STROKE 描边、Paint.Style.FILL_AND_STROKE)填充与描边
        paint.color=Color.RED
        //如果设置Paint.Style.STROKE，又设置paint.strokeWidth，则strokeWidth的宽度一半像素向内，一半像素向外，所以画出来的空心圆实际半径大小=设置圆的半径+strokeWidth/2
        //如果设置Paint.Style.FILL，即使设置paint.strokeWidth，画出来的圆半径大小=设置的圆半径大小
        //如果设置Paint.Style.FILL_AND_STROKE，即使设置paint.strokeWidth，画出来的圆半径大小=设置的圆半径大小
        paint.strokeWidth=10.dp2pxFloat
        paint.alpha=100//0-255，0为完全透明，255为完全不透明
        canvas?.drawCircle(300.dp2pxFloat,55.dp2pxFloat,50.dp2pxFloat,paint)
        paint.setShadowLayer(10.dp2pxFloat, 0f, 0f, Color.BLACK)
        canvas?.drawCircle(200.dp2pxFloat,55.dp2pxFloat,50.dp2pxFloat,paint)
    }
    fun setText(){
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        paint.textAlign = Paint.Align.CENTER; // 中心对齐

    }
}