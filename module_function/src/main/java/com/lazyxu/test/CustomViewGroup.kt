package com.lazyxu.test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * User:Lazy_xu
 * Date:2024/02/28
 * Description:
 * FIXME
 */
class CustomViewGroup (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 在 ViewGroup 中的 dispatchTouchEvent 方法用于分发触摸事件
        // 这里可以对事件进行预处理，例如判断是否需要拦截事件
        // 如果需要拦截事件，调用 onInterceptTouchEvent() 方法来判断是否拦截
        // 如果不拦截事件，将事件分发给子 View 的 onTouchEvent() 方法处理
        Log.d(
            "DispatchTag",
            "CustomViewGroup dispatchTouchEvent ${DispatchTouchUtils.action(ev)}"
        )
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 在 ViewGroup 中的 onInterceptTouchEvent 方法用于拦截触摸事件
        // 在这里可以判断是否需要拦截事件
        // 如果需要拦截事件，返回 true；否则返回 false
        Log.d(
            "DispatchTag",
            "CustomViewGroup onInterceptTouchEvent ${DispatchTouchUtils.action(ev)}"
        )
        return super.onInterceptTouchEvent(ev) // 返回 false 表示不拦截事件
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // 在 ViewGroup 中的 onTouchEvent 方法用于处理触摸事件
        // 如果事件没有被拦截，或者被拦截但是不消费，事件会传递到这里来处理
        // 在这里可以实现 ViewGroup 的触摸逻辑
        Log.d("DispatchTag", "CustomViewGroup onTouchEvent ${DispatchTouchUtils.action(ev)}")
        return super.onTouchEvent(ev) // 返回 true 表示消费了事件
    }
}