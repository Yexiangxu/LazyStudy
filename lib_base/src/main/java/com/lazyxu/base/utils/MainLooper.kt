package com.lazyxu.base.utils

import android.os.Handler
import android.os.Looper

class MainLooper private constructor(looper: Looper) : Handler(looper) {

    fun runOnUiThread(runnable: Runnable) {
        if (isInMainThread()) {
            runnable.run()
        } else {
            instance.post(runnable)
        }
    }

    fun runOnUiThread(runnable: Runnable, delayMillis: Long) {
        instance.postDelayed(runnable, delayMillis)
    }

    fun isInMainThread() = Looper.myLooper() == Looper.getMainLooper()

    companion object {
        val instance = MainLooper(Looper.getMainLooper())
    }
}