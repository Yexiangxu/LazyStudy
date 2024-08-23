package com.lazyxu.function.animation

import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.adapter.MainAdapter
import com.lazyxu.function.databinding.FunctionActivityAnimationTouchFeedbackBinding


@Route(path = ARouterPath.Function.ANIMATION_TOUCHFEEDBACK)
class TouchFeedbackActivity : BaseVbActivity<FunctionActivityAnimationTouchFeedbackBinding>() {

    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("触摸反馈动画")
        .build()


    override fun initView() {
        window.setBackgroundDrawable(null)
    }


}