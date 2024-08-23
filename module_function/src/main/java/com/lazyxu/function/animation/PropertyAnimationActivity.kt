package com.lazyxu.function.animation


import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionActivityAnimationPropertyBinding

/**
 * 属性动画
 */
@Route(path = ARouterPath.Function.ANIMATION_PROPERTY)
class PropertyAnimationActivity : BaseVbActivity<FunctionActivityAnimationPropertyBinding>() {
    private fun ValueAnimator.commonSetting() {
        duration = 2000
        repeatCount = 1
        repeatMode = ValueAnimator.REVERSE
        interpolator = LinearInterpolator()
        addUpdateListener {

        }
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
            }
        })
    }

    override fun initView() {
    }

    override fun initClicks() {
        mViewBinding.btnAlphat.setOnClickListener {
            startAnimation("alpha")
        }
        mViewBinding.btnXmlScale.setOnClickListener {
            val scaleAnimation =
                AnimatorInflater.loadAnimator(this, R.animator.propertyanimation_scale)
            scaleAnimation.setTarget(mViewBinding.ivTweenanimation)
            scaleAnimation.start()
        }
        mViewBinding.btnScale.setOnClickListener {
            val scaleX = ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "scaleX", 1f, 0f, 1f)
            val scaleY = ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "scaleY", 1f, 0f, 1f)
            val set = AnimatorSet()
            set.play(scaleX).with(scaleY)
//                .after(alphaAnimator).before(translationXAnimator)
            set.setDuration(3000)//都设置3s，也可以为每个单独设置
            set.setStartDelay(2000)
            set.start()
        }
        mViewBinding.btnTranslate.setOnClickListener {
            val alphaAnimation =
                ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "translationX", 0f, 300f)
            alphaAnimation.commonSetting()
            alphaAnimation.start()
        }

        mViewBinding.btnRotate.setOnClickListener {
            flipCard()
        }
        mViewBinding.btnXmlCombine.setOnClickListener {
            val set = AnimatorInflater.loadAnimator(this, R.animator.propertyanimation_combine);
            set.setTarget(mViewBinding.ivTweenanimation)
            set.setDuration(2000)
            set.start()
        }
        mViewBinding.btnKotlinCombine.setOnClickListener {
            val alphaAnimator =
                ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "alpha", 1f, 0f, 1f);
            val scaleXAnimator =
                ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "scaleX", 1f, 0.5f, 1f);
            val scaleYAnimator =
                ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "scaleY", 1f, 0.5f, 1f);
            val translationXAnimator =
                ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, "translationX", 0f, 200f, 0f);
            val set = AnimatorSet()
            set.play(scaleXAnimator).with(scaleYAnimator).after(alphaAnimator)
                .before(translationXAnimator)
            set.setDuration(3000)//都设置3s，也可以为每个单独设置
            set.setStartDelay(1000)
            set.start()
        }
        mViewBinding.btnViewpropertyanimator.setOnClickListener {
            mViewBinding.ivTweenanimation.animate().scaleX(2f).scaleY(2f).alpha(0.5f)
                .setDuration(2000).setStartDelay(2000).start()
        }
        mViewBinding.btnPropertyvaluesholder.setOnClickListener {
            propertyValuesHolder(mViewBinding.ivTweenanimation)
        }
        mViewBinding.btnValueanimator.setOnClickListener {
            ValueAnimator.ofFloat(1f, 0f, 1f).apply {
                duration = 2000
                addUpdateListener { animation ->
                    val animatedValue = animation.animatedValue as Float
                    mViewBinding.ivTweenanimation.alpha = animatedValue // 手动更新属性
                }
                start()
            }
        }
    }

    private fun startAnimation(type: String) {
        val alphaAnimation = ObjectAnimator.ofFloat(mViewBinding.ivTweenanimation, type, 1f, 0f, 1f)
        alphaAnimation.commonSetting()
        alphaAnimation.start()
    }

    private fun propertyValuesHolder(view: View) {
        val keyframe1 = Keyframe.ofFloat(1f, 0f)
        keyframe1.interpolator = BounceInterpolator()
        val keyframe2 = Keyframe.ofFloat(0f, 1f)
        keyframe2.interpolator = LinearInterpolator()
        val pvhX = PropertyValuesHolder.ofKeyframe("alpha", keyframe1, keyframe2)
        val pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f)
        val pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f)
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start()
    }

    private var isFrontShowing = true
    private fun flipCard() {
        val scale = applicationContext.resources.displayMetrics.density
        mViewBinding.cardFront.cameraDistance = 8000 * scale
        mViewBinding.cardBack.cameraDistance = 8000 * scale
        // 正面卡片翻出动画
        val frontFlipOut = ObjectAnimator.ofFloat(mViewBinding.cardFront, "rotationY", 0f, 90f)
        frontFlipOut.duration = 250
        // 反面卡片翻入动画
        val backFlipIn = ObjectAnimator.ofFloat(mViewBinding.cardBack, "rotationY", -90f, 0f)
        backFlipIn.duration = 250
        // 在正面卡片翻出动画结束时，将其隐藏，并显示反面卡片
        frontFlipOut.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                mViewBinding.cardFront.visibility = View.GONE
                mViewBinding.cardBack.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        // 反面卡片翻出动画
        val backFlipOut = ObjectAnimator.ofFloat(mViewBinding.cardBack, "rotationY", 0f, 90f)
        backFlipOut.duration = 250
        // 正面卡片翻入动画
        val frontFlipIn = ObjectAnimator.ofFloat(mViewBinding.cardFront, "rotationY", -90f, 0f)
        frontFlipIn.duration = 250
        // 在反面卡片翻出动画结束时，将其隐藏，并显示正面卡片
        backFlipOut.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                mViewBinding.cardBack.visibility = View.GONE
                mViewBinding.cardFront.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        val animatorSet = AnimatorSet()
        if (isFrontShowing) {
            animatorSet.play(backFlipIn).after(frontFlipOut)
        } else {
            animatorSet.play(frontFlipIn).after(backFlipOut)
        }
        animatorSet.start()
        isFrontShowing = !isFrontShowing
    }
}

