package com.lazyxu.base.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.AlarmClock
import android.text.TextUtils
import android.widget.Toast
import java.util.*


object SystemSettingUtils {
    /**
     *需要添加闹钟权限
    <uses-permission android:name="android.permission.SET_ALARM" />
    <uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
     */
    fun setSystemAlarmClock(context: Context, message: String, hour: Int, minute: Int) {
        try {
            cancelAlarm(context, message, hour, minute)
            val days: ArrayList<Int> = ArrayList()
            days.add(Calendar.SUNDAY)
            days.add(Calendar.MONDAY)
            days.add(Calendar.TUESDAY)
            days.add(Calendar.WEDNESDAY)
            days.add(Calendar.THURSDAY)
            days.add(Calendar.FRIDAY)
            days.add(Calendar.SATURDAY)
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, message)
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour)
            intent.putExtra(AlarmClock.EXTRA_DAYS, true)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minute)
            intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            intent.putExtra(AlarmClock.EXTRA_VIBRATE, true)
            intent.putExtra(AlarmClock.EXTRA_DAYS, days)
            context.startActivity(intent)
        } catch (e: Exception) {
            //打开系统设置
            val alarmas = Intent(AlarmClock.ACTION_SET_ALARM)
            context.startActivity(alarmas)
        }
    }

    private fun cancelAlarm(context: Context, message: String, hour: Int, minute: Int) {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(context, "手机版本过低,需手动取消闹钟", Toast.LENGTH_SHORT).show();
        } else {
            val i = Intent(AlarmClock.ACTION_DISMISS_ALARM)
            i.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_LABEL)
            if (!TextUtils.isEmpty(message)) { //通过标签取消
                i.putExtra(AlarmClock.EXTRA_MESSAGE, message)
            } else { //通过小时分钟取消
                i.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_TIME)
                i.putExtra(AlarmClock.EXTRA_IS_PM, hour > 12) //其中false = AM且true = PM
                i.putExtra(AlarmClock.EXTRA_HOUR, if (hour > 12) hour - 12 else hour)
                i.putExtra(AlarmClock.EXTRA_MINUTES, minute)
            }
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }

    }

}