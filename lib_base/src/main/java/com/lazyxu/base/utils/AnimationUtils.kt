package com.lazyxu.base.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

object AnimationUtils {
    /**
     * 缩放动画
     */
    fun startAnim(view: View?, duration: Long = 300) {
        if (view == null)
            return

        // 放大小时view，完全显示后开始呼吸效果
        val enterAnim = ScaleAnimation(
            0.9f,
            1.1f,
            0.9f,
            1.1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        enterAnim.duration = duration // 默认只执行一遍
        enterAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val anim = ScaleAnimation(
                    1.0f,
                    0.9f,
                    1.0f,
                    0.9f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                anim.duration = duration
                anim.repeatMode = Animation.REVERSE // 放大并缩小，时间为750*2
                anim.repeatCount = Animation.INFINITE // 无限循环
                view.animation = anim
                view.startAnimation(view.animation)
            }
        })

        view.startAnimation(enterAnim)
    }
}