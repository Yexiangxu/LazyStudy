package com.lazyxu.function.animation.transition

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.Toast
import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.Constants
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionAcitivityAnimationTransitionDetailBinding
import com.transitionseverywhere.ChangeText
import com.transitionseverywhere.Recolor
import com.transitionseverywhere.extra.Scale

@Route(path = ARouterPath.Function.ANIMATION_TRANSITION_DETAIL)
class TransitionAnimationDetailViewActivity :
    BaseVbActivity<FunctionAcitivityAnimationTransitionDetailBinding>() {
    private var type: String? = ""
    private val color1 = Color.parseColor("#FF4081")
    private val color2 = Color.parseColor("#3F51B5")
    override fun initView() {
        type = intent.getStringExtra(Constants.VIEW_KEY)
        mViewBinding.btnNormal.setOnClickListener(viewClick)
        mViewBinding.btnAnimation.setOnClickListener(viewClick)
    }

    private var mNormal = false
    private var mAnimation = false
    private val viewClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_normal -> {
                mNormal = !mNormal
                when (type) {
                    Constants.VIEW_VISIBLE, Constants.VIEW_FADE -> mViewBinding.tvNormal.visibility =
                        if (mNormal) View.VISIBLE else View.GONE

                    Constants.VIEW_MOVE -> {
                        val params =
                            mViewBinding.btnNormal.layoutParams as LinearLayout.LayoutParams
                        params.gravity =
                            if (mNormal) (Gravity.RIGHT or Gravity.TOP) else (Gravity.LEFT or Gravity.TOP)
                        mViewBinding.tvNormal.setLayoutParams(params)
                    }

                    Constants.VIEW_SCALE -> {
                        TransitionManager.beginDelayedTransition(mViewBinding.linearNormal, Scale())
                        mViewBinding.tvNormal.visibility =
                            if (mNormal) View.VISIBLE else View.INVISIBLE
                    }

                    Constants.View_RECOLOR -> {
                        mViewBinding.btnNormal.setTextColor(
                            if (!mNormal) color1 else color2

                        )
                        mViewBinding.btnNormal.setBackgroundColor(
                            if (!mNormal) color2 else color1

                        )
                    }

                    Constants.CHANGETEXT -> mViewBinding.btnNormal.setText(if (mNormal) "哈喽，我要变化了" else "嗨，我也要变化了")
                }
            }

            R.id.btn_animation -> {
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
                when (type) {
                    Constants.VIEW_VISIBLE -> animationVisible()
                    Constants.VIEW_MOVE -> animationMove()
                    Constants.VIEW_SLIDE -> animationSlide()
                    Constants.VIEW_SCALE -> animationScaleSet()
                    Constants.View_RECOLOR -> colorChange()
                    Constants.CHANGETEXT -> textChange()
                }
            }
        }
    }

    private fun animationVisible() {
        mAnimation = !mAnimation
        val transition: Transition = AutoTransition()
        transition.setDuration(500)
        transition.setInterpolator(LinearInterpolator())
        transition.setStartDelay(500)
        TransitionManager.beginDelayedTransition(mViewBinding.linearAnimation, transition)
        mViewBinding.tvAnimation.visibility = if (mAnimation) View.VISIBLE else View.GONE
    }

    private fun animationMove() {
        mAnimation = !mAnimation
        val transition: Transition = ChangeBounds()//这里也可直接使用 AutoTransition 实现
        transition.setDuration(1500L)
        TransitionManager.beginDelayedTransition(mViewBinding.linearAnimation, transition)
        val params = mViewBinding.btnAnimation.layoutParams as LinearLayout.LayoutParams
        params.gravity =
            if (mAnimation) (Gravity.END or Gravity.TOP) else (Gravity.START or Gravity.TOP)
        mViewBinding.btnAnimation.setLayoutParams(params)
    }

    private fun animationSlide() {
        mAnimation = !mAnimation
        TransitionManager.beginDelayedTransition(mViewBinding.linearAnimation, Slide(Gravity.END))
        mViewBinding.tvAnimation.visibility = if (mAnimation) View.VISIBLE else View.GONE
    }

    private fun animationScaleSet() {
        mAnimation = !mAnimation
        val set = TransitionSet()
            .addTransition(Fade())
            .setInterpolator(if (mAnimation) LinearInterpolator() else LinearInterpolator())
        TransitionManager.beginDelayedTransition(mViewBinding.linearAnimation, set)
        mViewBinding.tvAnimation.visibility = if (mAnimation) View.VISIBLE else View.INVISIBLE
    }

    private fun colorChange() {
        mAnimation = !mAnimation
        TransitionManager.beginDelayedTransition(mViewBinding.linearAnimation, Recolor())
        mViewBinding.btnAnimation.setTextColor(
            if (!mAnimation) color1 else color2
        )
        mViewBinding.btnAnimation.setBackgroundColor(
            if (!mAnimation) color2 else color1
        )
    }

    private fun textChange() {
        mAnimation = !mAnimation
        TransitionManager.beginDelayedTransition(
            mViewBinding.linearAnimation,
            ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN)
        )
        mViewBinding.btnAnimation.setText(if (mAnimation) "哈喽，我要变化了" else "嗨，我也要变化了")
    }
}
