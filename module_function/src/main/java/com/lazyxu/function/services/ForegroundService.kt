package com.lazyxu.function.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.lazyxu.function.R


class ForegroundService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("My Service")
            .setContentText("Service is running in the foreground")
            .setSmallIcon(R.drawable.puppet)
            .build()

        startForeground(1, notification)  // 提升为前台服务

        // 执行长时间任务
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // 清理资源
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
