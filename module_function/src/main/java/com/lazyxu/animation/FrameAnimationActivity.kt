package com.lazyxu.animation

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.FunctionActivityAnimationFrameBinding

/**
 * 这里仅作为示例展示，实际项目中不会用到帧动画，性能太差
 */
@Route(path = ARouterPath.Function.ANIMATION_FRAME)
class FrameAnimationActivity :BaseVbActivity<FunctionActivityAnimationFrameBinding>(){
    private lateinit var animationDrawable:AnimationDrawable
    override fun initView() {
        val animationDrawable=mViewBinding.ivAnimation.drawable as AnimationDrawable
        mViewBinding.btnStart.setOnClickListener {
            animationDrawable.start()
        }
        mViewBinding.btnStop.setOnClickListener {
            animationDrawable.stop()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        for (i in 0 until animationDrawable.numberOfFrames) {
            (animationDrawable.getFrame(i) as BitmapDrawable).bitmap.recycle()
        }
        animationDrawable.stop()
    }
}