package com.lazyxu.function.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BindService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return Binder()  // 返回一个Binder实例，供客户端使用
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // 清理操作
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 清理资源
    }
}