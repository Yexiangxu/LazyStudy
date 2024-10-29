package com.lazyxu.base.utils

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object ThreadUtils {
    /**
     * 减少核心数，降低资源使用
     */
    private val scheduleExecutors by lazy {
        Executors.newScheduledThreadPool(2)
    }

    /**
     * 用于执行延时任务，任务将被放在子线程执行
     * delayMill:毫秒
     */
    @JvmStatic
    fun delayInSubThread(delayMill: Long, task: () -> Unit) {
        scheduleExecutors.schedule(
            { task.invoke() },
            delayMill,
            TimeUnit.MILLISECONDS
        )
    }

    @JvmStatic
    fun nowInSubThread(task: () -> Unit) {
        scheduleExecutors.schedule(
            { task.invoke() },
            0,
            TimeUnit.SECONDS
        )
    }

    @JvmStatic
    fun loopInSubThread(delayMill: Long, task: () -> Unit) {
        scheduleExecutors.schedule(
            {
                task.invoke()
                loopInSubThread(delayMill, task)
            },
            delayMill,
            TimeUnit.MILLISECONDS
        )
    }

}