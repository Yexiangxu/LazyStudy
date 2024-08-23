package com.lazyxu.function.animation.transition

import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.databinding.FunctionActivityAnimationShareelementsBinding


@Route(path = ARouterPath.Function.ANIMATION_SHAREELEMENTS)
class ShareElementsActivity : BaseVbActivity<FunctionActivityAnimationShareelementsBinding>() {
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("元素共享动画")
        .build()

    override fun initView() {
    }
}