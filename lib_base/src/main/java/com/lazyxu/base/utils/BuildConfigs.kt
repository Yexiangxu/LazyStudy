package com.lazyxu.base.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import com.lazyxu.base.BuildConfig
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogUtils
import java.io.File

/**
 * User:Lazy_xu
 * Date:2024/04/02
 * Description: 用来防止BuildConfig相关配置，即编译相关配置
 * FIXME
 */
object BuildConfigs {
    /**
     * 所有判断 BuildConfigs.DEBUG 的用该方法
     * 测试环境打开日志log，线上环境关闭日志log，但有特殊情况如查看线上日志，可放入 debug文件 可打开线上日志
     * 而且可能还有别的情况，比如 主动更换判断是否是debug，而不是通过 BuildConfig.DEBUG，所以统一封装在此处
     *
     */
    val IS_DEV by lazy {
       BuildConfig.DEBUG||isDebugEnvironment(BaseApplication.INSTANCE)
    }

    /**
     * 针对测试开放的开口
     * @return
     */
    private fun isDebugEnvironment(context: Context): Boolean {
        try {
            val file = File(getSdcardPath(context) + "/" + "realization123" + ".txt")
            return file.exists()
        } catch (_: Throwable) {
        }
        return false
    }

    /** 获取SD卡路径 */
    private fun getSdcardPath(context: Context): String? {
        try {
            // Q的分区存储变更，是针对应用的目标平台，并非系统版本，所以再加上targetSdkVersion
            return if (Build.VERSION.SDK_INT >= 29 && context.applicationInfo.targetSdkVersion >= 29) {
                // Q开始，使用/sdcard/Android/data/<pkgName>/files/
                context.getExternalFilesDir(null)?.absolutePath
            } else {
                // Q之前，使用Environment.getExternalStorageDirectory().getAbsolutePath();
                Environment.getExternalStorageDirectory().absolutePath
            }
        } catch (_: Throwable) {
        }
        return null
    }
}