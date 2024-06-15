package com.lazyxu.test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * User:Lazy_xu
 * Date:2024/02/28
 * Description:
 * FIXME
 */
class CustomView (context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // 在 View 中的 onTouchEvent 方法用于处理触摸事件
        // 如果事件在 ViewGroup 中没有被拦截，或者被拦截但是不消费，事件会传递到这里来处理
        // 在这里可以实现 View 的触摸逻辑
        Log.d("DispatchTag", "CustomView onTouchEvent ${DispatchTouchUtils.action(ev)}")
        return super.onTouchEvent(ev) // 返回 true 表示消费了事件
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(
            "DispatchTag",
            "CustomView dispatchTouchEvent ${DispatchTouchUtils.action(ev)}"
        )
        return super.dispatchTouchEvent(ev)
    }

}