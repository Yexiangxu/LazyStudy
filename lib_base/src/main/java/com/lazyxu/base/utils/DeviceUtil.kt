package com.lazyxu.base.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogUtils

object DeviceUtil {
    fun printDeviceDetailInfo(context: Context) {
        if (!BuildConfigs.IS_DEV) return

        val displayMetrics = context.resources.displayMetrics
        LogUtils.d("displayMetrics=$displayMetrics")
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = activityManager.memoryClass // 以MB为单位的内存限制
        val largeMemoryClass = activityManager.largeMemoryClass // 使用largeHeap时更高的内存限制

        LogUtils.d("内存=$memoryClass},最大内存=${largeMemoryClass}")

    }



    /**
     * 是否深色主题
     * 切记 Context 不能用 Application
     */
    fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }


    /**
     * 品牌
     */
    val BRAND: String by lazy {
        Build.BRAND
    }

    /**
     * 机型
     */
    val MODLE: String by lazy {
        Build.MODEL
    }

    /**
     * 系统
     */
    val DEVICE_OS: String by lazy {
        Build.VERSION.RELEASE
    }


    /**
     * 获取版本号
     */
    val VERSION_CODE by lazy {
        try {
            val packageInfo: PackageInfo = BaseApplication.INSTANCE.packageManager.getPackageInfo(
                BaseApplication.INSTANCE.packageName,
                0
            )
            val versionCode: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                packageInfo.versionCode
            }
            versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            -1
        }
    }

    val VERSION_NAME: String by lazy {
        try {
            val packageInfo: PackageInfo = BaseApplication.INSTANCE.packageManager.getPackageInfo(BaseApplication.INSTANCE.packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "1.0.0"
        }
    }
}
