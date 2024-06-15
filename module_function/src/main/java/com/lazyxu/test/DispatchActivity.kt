package com.lazyxu.test

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.lazyxu.function.R

/**
 * User:Lazy_xu
 * Date:2024/02/27
 * Description: 验证事件分发
 * FIXME
 */
class DispatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.function_activity_dispatch)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("DispatchTag", "Activity dispatchTouchEvent ${DispatchTouchUtils.action(ev)}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        MotionEvent.ACTION_DOWN
        Log.d("DispatchTag", "Activity onTouchEvent ${DispatchTouchUtils.action(event)}")
        return super.onTouchEvent(event)
    }


}