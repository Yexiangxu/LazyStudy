package com.lazyxu.animation

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionActivityAnimationTweenBinding

/**
 * 这里仅作为示例展示，实际项目中不会用到帧动画，性能太差
 */
@Route(path = ARouterPath.Function.ANIMATION_TWEEN)
class TweenAnimationActivity : BaseVbActivity<FunctionActivityAnimationTweenBinding>() {
    private fun Animation.commonSetting():Animation{
        return this.apply {
            duration = 2000
            repeatCount = 1
            repeatMode = Animation.REVERSE
            fillAfter = false
            interpolator = LinearInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
    }
    private val translateAnimation = TranslateAnimation(
        Animation.RELATIVE_TO_SELF,
        0f,
        Animation.RELATIVE_TO_SELF,
        1f,
        Animation.RELATIVE_TO_SELF,
        0f,
        Animation.RELATIVE_TO_SELF,
        0f
    ).commonSetting()
    private val alphaAnimation = AlphaAnimation(1f, 0.1f).commonSetting()
    private val scaleAnimation = ScaleAnimation(
        1.0f,
        0.5f,
        1.0f,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    ).commonSetting()
    private val rotateAnimation = RotateAnimation(
        0f,
        270f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    ).commonSetting()
    override fun initView() {
        mViewBinding.btnAlphat.setOnClickListener {

            mViewBinding.ivTweenanimation.startAnimation(alphaAnimation)
        }
        mViewBinding.btnXmlScale.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(
                this@TweenAnimationActivity,
                R.anim.anim_scale
            )
            mViewBinding.ivTweenanimation.startAnimation(animation)
        }
        mViewBinding.btnScale.setOnClickListener {

            mViewBinding.ivTweenanimation.startAnimation(scaleAnimation)
        }

        mViewBinding.btnTranslate.setOnClickListener {

            mViewBinding.ivTweenanimation.startAnimation(translateAnimation)
        }
        mViewBinding.btnRotate.setOnClickListener {

            mViewBinding.ivTweenanimation.startAnimation(rotateAnimation)
        }
        mViewBinding.btnXmlCombine.setOnClickListener {
            val animatorSet=AnimationUtils.loadAnimation(this@TweenAnimationActivity,R.anim.anim_combine)
            mViewBinding.ivTweenanimation.startAnimation(animatorSet)
        }
        mViewBinding.btnKotlinCombine.setOnClickListener {
            alphaAnimation.startOffset = 2000
            translateAnimation.startOffset = 4000
            scaleAnimation.startOffset=6000
            rotateAnimation.startOffset=8000
            val animationSet = AnimationSet(true).apply {
                addAnimation(alphaAnimation)
                addAnimation(translateAnimation)
                addAnimation(scaleAnimation)
                addAnimation(rotateAnimation)
                fillAfter = true
            }
            mViewBinding.ivTweenanimation.startAnimation(animationSet)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewBinding.ivTweenanimation.clearAnimation()
    }
}




