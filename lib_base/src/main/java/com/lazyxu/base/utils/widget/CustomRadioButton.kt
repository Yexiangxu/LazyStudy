package com.lazyxu.base.utils.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.lazyxu.base.R
import com.lazyxu.base.ext.dp2px

class CustomRadioButton : AppCompatRadioButton {
    private var mImg_width = 0f
    private var mImg_height = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val t = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton)
        mImg_width = t.getDimension(R.styleable.CustomRadioButton_rb_width, 109f.dp2px)
        mImg_height = t.getDimension(R.styleable.CustomRadioButton_rb_height, 123f.dp2px)
        t.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //让RadioButton的图标可调大小 属性：
        val drawableLeft = this.compoundDrawables[0] //获得文字左侧图片
        val drawableTop = this.compoundDrawables[1] //获得文字顶部图片
        val drawableRight = this.compoundDrawables[2] //获得文字右侧图片
        val drawableBottom = this.compoundDrawables[3] //获得文字底部图片
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, mImg_width.toInt(), mImg_height.toInt())
            setCompoundDrawables(drawableLeft, null, null, null)
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, mImg_width.toInt(), mImg_height.toInt())
            setCompoundDrawables(null, null, drawableRight, null)
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, mImg_width.toInt(), mImg_height.toInt())
            setCompoundDrawables(null, drawableTop, null, null)
        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, mImg_width.toInt(), mImg_height.toInt())
            setCompoundDrawables(null, null, null, drawableBottom)
        }
    }
}