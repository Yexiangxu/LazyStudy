package com.lazyxu.base.log

import com.lazyxu.base.utils.BuildConfigs
import com.orhanobut.logger.Logger

/**
 * User:Lazy_xu
 * Date:2023/11/09
 * Description: 日志封装在该类中，方便随时更换日志库
 * FIXME
 */
object LogUtils {

    fun d(msg: () -> Unit) {
        if (BuildConfigs.IS_DEV) {
            Logger.t(LogTag.COMMON).d(msg.invoke())
        }
    }

    @JvmStatic
    fun d(msg: String) {
        if (BuildConfigs.IS_DEV) {
            Logger.t(LogTag.COMMON).d(msg)
        }
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        if (BuildConfigs.IS_DEV) {
            Logger.t(tag).d(msg)
        }
    }

    @JvmStatic
    fun e(tag: String? = LogTag.COMMON, msg: String) {
        if (BuildConfigs.IS_DEV) {
            Logger.t(tag).e(msg)
        }
    }
}