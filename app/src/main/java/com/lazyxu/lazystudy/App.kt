package com.lazyxu.lazystudy

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils

class App : BaseApplication() {
    companion object {
    }

    override fun onCreate() {
        super.onCreate()
        //注册activity生命周期
        ProcessLifecycleOwner.get().lifecycle.addObserver(ForegroundChecker())
    }

    inner class ForegroundChecker : DefaultLifecycleObserver {
        var backgroundStamp = 0L
        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            LogUtils.d(LogTag.LIFECYCLE, "onAppBackground")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            LogUtils.d(LogTag.LIFECYCLE, "onAppForeground")
        }
    }
}