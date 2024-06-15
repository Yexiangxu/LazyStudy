//package com.lazyxu.base.aop.aspect
//
//import android.app.Activity
//import android.content.Context
//import android.view.View
//import androidx.fragment.app.Fragment
//import com.lazyxu.base.aop.annotation.CheckNet
//import com.lazyxu.base.utils.AppToast.show

//import com.lazyxu.base.utils.NetUtils
//import com.orhanobut.logger.Logger
//import org.aspectj.lang.ProceedingJoinPoint
//import org.aspectj.lang.annotation.Around
//import org.aspectj.lang.annotation.Aspect
//import org.aspectj.lang.annotation.Pointcut
//import org.aspectj.lang.reflect.MethodSignature
//
///**
// * 使用@Aspect注解，编译器在编译的时候就会自动去解析，并不需要主动去调用AspectJ类里面的代码
// */
//@Aspect
//class NetworkAspect {
//    /**
//     * 找到处理的切点 可以处理所有的方法
//     */
//    @Pointcut("execution(@com.lazyxu.base.aop.annotation.CheckNet * *(..))")
//    fun checkNetBehavior() {
//    }
//
//    /**
//     * 处理切面
//     */
//    @Around("checkNetBehavior()")
//    @Throws(Throwable::class)
//    fun checkNet(joinPoint: ProceedingJoinPoint): Any? {
//        val signature = joinPoint.signature as MethodSignature
//        val checkNet = signature.method.getAnnotation(CheckNet::class.java)
//        if (checkNet != null) {
//            val `object` = joinPoint.getThis()
//            val context = getContext(`object`)
//            if (context != null) {
//                if (!NetUtils.isNetworkConnected(context)) {
//                    show("请检查您的网络")
//                    return null
//                }
//            }
//        }
//        Logger.i(LogTags.ASPECT + "CheckNet")
//        //执行原方法
//        return joinPoint.proceed()
//    }
//
//    private fun getContext(context: Any): Context? {
//        return when (context) {
//            is Activity -> {
//                context
//            }
//            is Fragment -> {
//                context.activity
//            }
//            is View -> {
//                context.context
//            }
//            else -> null
//        }
//    }
//}