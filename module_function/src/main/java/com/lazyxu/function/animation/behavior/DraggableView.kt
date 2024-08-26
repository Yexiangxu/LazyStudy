package com.lazyxu.function.animation.behavior
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class DraggableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var lastX = 0f
    private var lastY = 0f

    init {
        // 使视图可以点击和拖动
        isClickable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 记录初始触摸位置
                lastX = event.rawX
                lastY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                // 计算手指移动的距离
                val dx = event.rawX - lastX
                val dy = event.rawY - lastY

                // 更新View的位置
                x += dx
                y += dy
//                x = event.rawX-width/2
//                y = event.rawY-height/2
                // 更新上次触摸位置
                lastX = event.rawX
                lastY = event.rawY
            }
        }
        return true
    }
}
