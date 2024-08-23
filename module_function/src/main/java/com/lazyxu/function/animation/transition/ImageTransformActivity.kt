package com.lazyxu.function.animation.transition

import android.view.View
import android.view.ViewGroup
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.ext.dp2px
import com.lazyxu.function.databinding.FunctionActivityImagetransformBinding


class ImageTransformActivity : BaseVbActivity<FunctionActivityImagetransformBinding>() {
    private var mAnimation = false


    fun show(view: View?) {
        mAnimation = !mAnimation
        TransitionManager.beginDelayedTransition(
            mViewBinding.transitionsContainer, TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())
        )
        val params: ViewGroup.LayoutParams = mViewBinding.image.layoutParams
        params.height = if (mAnimation) ViewGroup.LayoutParams.MATCH_PARENT else 150.dp2px
        mViewBinding.image.setLayoutParams(params)
    }

    override fun initView() {
    }
}
