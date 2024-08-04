package com.lazyxu.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.FunctionActivityAnimationPropertyBinding

/**
 * 属性动画
 */
@Route(path = ARouterPath.Function.ANIMATION_PROPERTY)
class PropertyAnimationActivity :BaseVbActivity<FunctionActivityAnimationPropertyBinding>(){
    override fun initView() {
        mViewBinding.btnAlphat.setOnClickListener {
            val alphaAnimation=ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation,"alpha",1f,0f,1f).apply {
                duration = 2000
                repeatCount = 1
                repeatMode = ValueAnimator.REVERSE
                interpolator = LinearInterpolator()
                addUpdateListener {

                }
                addListener(object: Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }
                })
            }
            alphaAnimation.start()
        }

    }
    override fun onDestroy() {
        super.onDestroy()

    }
}