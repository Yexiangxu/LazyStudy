package com.lazyxu.function.animation.behavior

import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout


import android.content.Context
import android.util.AttributeSet

/**

onInterceptTouchEvent: 拦截触摸事件，决定是否由 Behavior 处理触摸事件。
onTouchEvent: 处理触摸事件，如拖动、滑动等操作。
layoutDependsOn: 决定当前 Behavior 是否依赖于另一个 View。
onDependentViewChanged: 当依赖的 View 状态变化时调用，允许当前 View 做出响应
 */
class DraggableBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        // 拦截触摸事件
        return ev.action == MotionEvent.ACTION_DOWN
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        // 更新View的位置
        when (ev.action) {
            MotionEvent.ACTION_MOVE -> {
                child.x = ev.rawX - child.width / 2
                child.y = ev.rawY - child.height / 2
            }
        }
        return true
    }
}

