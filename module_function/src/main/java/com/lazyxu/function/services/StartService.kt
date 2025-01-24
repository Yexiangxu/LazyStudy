package com.lazyxu.function.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class StartService :Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY// 服务被杀死后会重启 START_STICKY;不会重启START_NOT_STICKY
    }
}