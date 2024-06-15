package com.lazyxu.base.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import android.text.TextUtils
import com.lazyxu.base.log.LogUtils

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * 进程工具类
 */
object ProcessUtils {

    /**
     * 获取当前所有进程
     *
     * @param context Context 上下文
     * @return List<ActivitysManager.RunningAppProcessInfo> 当前所有进程
     */
    fun getRunningAppProcessList(context: Context): List<ActivityManager.RunningAppProcessInfo> {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.runningAppProcesses
    }

    /**
     * 判断该进程id是否属于该进程名的进程
     *
     * @param context Context 上下文
     * @param processId Int 进程Id
     * @param processName String 进程名
     * @return Boolean
     */
    fun isPidOfProcessName(context: Context, processId: Int, processName: String): Boolean {
        // 遍历所有进程找到该进程id对应的进程
        for (process in getRunningAppProcessList(context)) {
            if (process.pid == processId) {
                // 判断该进程id是否和进程名一致
                return (process.processName == processName)
            }
        }
        return false
    }

    /**
     * 获取主进程名
     *
     * @param context Context 上下文
     * @return String 主进程名
     * @throws PackageManager.NameNotFoundException if a package with the given name cannot be found on the system.
     */
    @Throws(PackageManager.NameNotFoundException::class)
    fun getMainProcessName(context: Context): String {
        val applicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
        return applicationInfo.processName
    }

    /**
     * 判断当前进程是否是主进程
     *
     * @param context Context 上下文
     * @return Boolean
     * @throws PackageManager.NameNotFoundException if a package with the given name cannot be found on the system.
     */

    fun isMainProcess(context: Context): Boolean {
        val processName = getProcessName(Process.myPid())
        LogUtils.d("isMainProcess=${processName == context.packageName},processName=$processName,packageName=${context.packageName}")
        return processName == context.packageName
    }

    fun getProcessName(pid: Int): String {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return ""
    }
}