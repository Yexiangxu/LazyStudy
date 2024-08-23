package com.lazyxu.function.animation

import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.animation.ValueAnimator
import android.graphics.PointF
import android.view.animation.PathInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.ext.dp2pxFloat
import com.lazyxu.function.animation.interpolator.BezierInterpolator
import com.lazyxu.function.animation.typeevaluator.ColorEvaluator
import com.lazyxu.function.animation.typeevaluator.ExponentialDecayEvaluator
import com.lazyxu.function.databinding.FunctionActivityAnimationCustomBinding

/**
 * 这里仅作为示例展示，实际项目中不会用到帧动画，性能太差
 */
@Route(path = ARouterPath.Function.ANIMATION_CUSTOM)
class CustomAnimationActivity : BaseVbActivity<FunctionActivityAnimationCustomBinding>() {


    override fun initView() {
        val customInterpolator = PathInterpolator(0f, 1f, 1f, 0f)
        mViewBinding.btnPathinterpolator.setOnClickListener {
            ObjectAnimator.ofFloat(
                mViewBinding.ivTweenanimation,
                "translationX",
                0f,
                300.dp2pxFloat
            ).apply {
                interpolator = customInterpolator // 使用你选择的插值器
                repeatMode = ValueAnimator.REVERSE
                duration = 1000 // 动画持续时间1秒
                start()
            }
        }
        mViewBinding.btnBezier.setOnClickListener {
            ObjectAnimator.ofFloat(
                mViewBinding.ivTweenanimation,
                "translationX",
                0f,
                300.dp2pxFloat
            ).apply {
                interpolator = BezierInterpolator(0f, 1f, 1f, 0f) // 使用你选择的插值器
                repeatMode = ValueAnimator.REVERSE
                duration = 1000 // 动画持续时间1秒
                start()
            }
        }
        mViewBinding.btnCustom1.setOnClickListener {
            ObjectAnimator.ofFloat(
                mViewBinding.customCircle,
                "radius",
                50.dp2pxFloat,
                100.dp2pxFloat
            )
                .apply {
                    repeatMode = ValueAnimator.REVERSE
                    repeatCount = 1
                    duration = 2000 // 动画持续时间1秒
                    start()
                }
        }
        mViewBinding.btnCustom2.setOnClickListener {
            val colorAnimation = ValueAnimator.ofObject(ColorEvaluator(), 0xffff0000.toInt(), 0xff0000ff.toInt())
            colorAnimation.duration = 3000 // 3秒
            colorAnimation.addUpdateListener { animator ->
                mViewBinding.customCircle.setBackGroudColor(animator.animatedValue as Int)
            }
            colorAnimation.start()
        }
        mViewBinding.btnCustom3.setOnClickListener{
//            val startPoint = PointF(0f, 0f)
//            val endPoint = PointF(500f, 500f)
//            val pointAnimator = ValueAnimator.ofObject(PointFEvaluator(), startPoint, endPoint)
//            pointAnimator.duration = 3000 // 3秒
//            pointAnimator.addUpdateListener { animator ->
//                val point = animator.animatedValue as PointF
//                // 将视图的位置移动到当前计算出的点
//                mViewBinding.customCircle.translationX = point.x
//                mViewBinding.customCircle.translationY = point.y
//            }
//            pointAnimator.start()
//            val startPoint = PointF(0f, 0f)
//            val endPoint = PointF(500f, 500f)

            val pointAnimator = ValueAnimator.ofObject(ExponentialDecayEvaluator(), 0f, 1000f)
            pointAnimator.duration = 3000 // 3秒
            pointAnimator.addUpdateListener { animator ->
                val point = animator.animatedValue as Float
                // 将视图的位置移动到当前计算出的点
                mViewBinding.customCircle.translationY = point
            }

            pointAnimator.start()

        }
    }
}




