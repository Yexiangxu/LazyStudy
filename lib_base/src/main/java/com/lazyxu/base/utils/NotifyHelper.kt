package com.lazyxu.base.utils


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.lazyxu.base.R
import com.lazyxu.base.log.LogUtils

object NotifyHelper {
    /**
     *   Android 8.0+ 必须创建通知渠道
     */
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                channelId,
                context.getString(R.string.app_name),
                importance
            ).apply {
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                lightColor = Color.RED;
                setVibrationPattern(longArrayOf(0, 500, 250, 500))
                enableLights(true)
                lightColor = Color.BLUE
                enableVibration(true)
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private val channelId = "lazyxu_channel"

    /**
     * 从 Android 13（API 33）开始，需要动态申请通知权限，否则通知不会显示
     */

    fun sendNotification(context: Context, smallIcon: Int): Notification {
        createNotificationChannel(context)
        // 2. 创建点击通知时跳转的 Intent（这里是跳转 MainActivity）
        val intent = Intent(context, Class.forName("com.hy.lqsg.MainActivity")).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        // 创建自定义布局  自定义布局不能在attachBaseContext中
//        val customView = RemoteViews(context.packageName, R.layout.notify_withdraw).apply {
//            setTextViewText(
//                R.id.tv_notify,
//                "自定义通知"
//            )
//        }
        // 3. 构建通知内容
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(smallIcon)   // 小图标必须设置
            .setAutoCancel(true)             // 点击后自动消失
//            .setCustomContentView(customView)
            .setContentIntent(pendingIntent)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(context.getString(R.string.app_name))
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setOngoing(false)//✅ 是否是前台常驻通知（如音乐播放器）
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(ContextCompat.getColor(context, R.color.colorAccent))
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//锁屏通知可见性
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("这是展开后的长文本通知内容，可以展示更多信息，适合内容较多的通知")
            ) // ✅ 支持展开的大文本通知样式
            .setColor(Color.BLUE)                        // ✅ 设置图标颜色（部分 ROM 有效）
            .setDefaults(Notification.DEFAULT_ALL)      // ✅ 默认声音、震动、闪光灯
            .setOnlyAlertOnce(true)
        val notificationManager = NotificationManagerCompat.from(context)
        val notification = builder.build()
        if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            notificationManager.notify(1001, notification)
            LogUtils.d("NotifyTag", "1刷新了通知")
        } else {
            LogUtils.d("NotifyTag", "1通知权限未开启")
        }
        return notification

    }
}
