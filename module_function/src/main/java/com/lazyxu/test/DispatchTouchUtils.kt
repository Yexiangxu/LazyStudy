package com.lazyxu.test

import android.view.MotionEvent

/**
 * User:Lazy_xu
 * Date:2024/02/28
 * Description:
 * FIXME
 */
object DispatchTouchUtils {
    fun action(ev: MotionEvent?):String{
        return when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                "ACTION_DOWN"
            }

            MotionEvent.ACTION_UP -> {
                "ACTION_UP"
            }

            MotionEvent.ACTION_MOVE -> {
                "ACTION_MOVE"
            }

            MotionEvent.ACTION_CANCEL -> {
                "ACTION_CANCEL"
            }
            else -> "Other"
        }
    }
}