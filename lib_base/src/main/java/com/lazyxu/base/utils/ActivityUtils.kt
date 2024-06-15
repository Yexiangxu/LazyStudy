package com.lazyxu.base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import java.text.SimpleDateFormat

object ActivityUtils {
    /**
     * 返回home
     */

    fun startHomeActivity(context: Context) {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(homeIntent)

    }

    fun isActivityAlive(activity: Activity?): Boolean {
        return (activity != null && !activity.isFinishing && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed))
    }
}