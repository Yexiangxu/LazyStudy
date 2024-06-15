//package com.lazyxu.base.aop.aspect
//
//import android.view.View
//import com.lazyxu.base.R

//import com.orhanobut.logger.Logger
//import org.aspectj.lang.ProceedingJoinPoint
//import org.aspectj.lang.annotation.Around
//import org.aspectj.lang.annotation.Aspect
//import java.util.*
//
///**
// * 防止View被连续点击,间隔时间600ms
// */
//@Aspect
//class SingleClickAspect {
//    /**
//     * Aroud表示方法前后各插入代码，包含Before和After的全部功能
//     * 第一个 * 表示返回值为任意类型
//     * (..)为任意类型,()里面为方法的参数
//     */
//    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
//    @Throws(Throwable::class)
//    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint) {
//        var view: View? = null
//        for (arg in joinPoint.args) {
//            if (arg is View) {
//                view = arg
//                break
//            }
//        }
//        if (view != null) {
//            val tag = view.getTag(TIME_TAG)
//            val lastClickTime = if (tag != null) tag as Long else 0
//            Logger.i(LogTags.ASPECT + "LastClickTime:" + lastClickTime)
//            val currentTime = Calendar.getInstance().timeInMillis
//            if (currentTime - lastClickTime > CLICK_DELAY_TIME) {
//                view.setTag(TIME_TAG, currentTime)
//                Logger.i(LogTags.ASPECT + "CurrentClickTime:" + currentTime)
//                joinPoint.proceed() //执行原方法
//            } else {
//                Logger.i(LogTags.ASPECT + "防重点击起效")
//            }
//        }
//    }
//
//    companion object {
//        var TIME_TAG = R.id.click_time
//        private const val CLICK_DELAY_TIME = 600
//    }
//}