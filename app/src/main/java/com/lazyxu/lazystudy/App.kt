package com.lazyxu.lazystudy

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogUtils

class App : BaseApplication() {
    companion object {
    }

    override fun onCreate() {
        super.onCreate()
        //注册activity生命周期
        ProcessLifecycleOwner.get().lifecycle.addObserver(ForegroundChecker())
    }

    inner class ForegroundChecker : LifecycleObserver {
        var backgroundStamp = 0L

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private fun onAppBackground() {
            LogUtils.d("onAppBackground")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun onAppForeground() {
            LogUtils.d("onAppForeground")

        }
    }
}