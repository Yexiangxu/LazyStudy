package com.lazyxu.base.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * User:Lazy_xu
 * FIXME
 */
object ActivitysManager  {
    private var mActivities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        this.mActivities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (mActivities.isNotEmpty() && mActivities.contains(activity)) {
            mActivities.remove(activity)
        }
    }
    fun isActivityDestory(context: Context): Boolean {
        val activity = findActivity(context)
        return if (activity != null) {
            activity.isDestroyed || activity.isFinishing
        } else true
    }
    private fun findActivity(context: Context): Activity? {
        // 怎么判断context是不是Activity
        if (context is Activity) { // 这种方法不够严谨
            return context
        } else if (context is ContextWrapper) {
            return findActivity(context.baseContext)
        }
        return null
    }

//    /**
//     * 获取当前Activity (堆栈中最后一个添加的)
//     */
//    val currentActivity: Activity?
//        get() = mActivities.lastElement()
//
//    /**
//     * 获取指定类名的Activity
//     */
//    fun getActivity(cls: Class<*>): Activity? {
//        mActivities.forEach {
//            if (it.javaClass == cls) {
//                return it
//            }
//        }
//        return null
//    }
//
//
//    fun finishActivity(activity: Activity) {
//        with(activity) {
//            if (!mActivities.isNullOrEmpty() && mActivities.contains(this)) {
//                mActivities.remove(this)
//                this.finish()
//            }
//        }
//    }
//
//    /**
//     * 结束指定类名的Activity
//     *
//     */
//    fun finishActivity(clazz: Class<*>) {
//        for (activity in mActivities) {
//            if (activity?.javaClass == clazz) {
//                finishActivity(activity)
//                return
//            }
//        }
//    }
//
//    /**
//     * 退出栈并finish当前activity
//     */
//    private fun finishCurrentActivity() {
//        if (this.mActivities.isNotEmpty()) {
//            //pop返回栈顶值并将其删除
//            (mActivities.pop() as Activity).finish()
//        }
//    }
//
//
//    fun exitApp(isForceClose: Boolean) {
//        while (this.mActivities.size > 0) {
//            this.finishCurrentActivity()
//        }
//        if (isForceClose) {
//            Process.killProcess(Process.myPid())
//            exitProcess(-1)
//        }
//    }
//
//
//    fun finishAllActivityExceptCurrent() {
//        while (this.mActivities.size > 1) {
//            val activity: Activity = this.mActivities[0] as Activity
//            this.mActivities.remove(activity)
//            activity.finish()
//        }
//    }
//
//    /**
//     * 结束某个Activity之外的所有Activity
//     */
//    fun finishAllActivityExcept(clazz: Class<*>) {
//        for (i in mActivities.indices.reversed()) {
//            if (mActivities[i] != null && mActivities[i]?.javaClass != clazz) {
//                finishActivity(mActivities[i])
//            }
//        }
//    }
}
