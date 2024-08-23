package com.lazyxu.function.animation.transition

import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.utils.decoration.GridSpacingItemDecoration
import com.lazyxu.function.adapter.ScencesAdapter
import com.lazyxu.function.databinding.FunctionActivityAnimationScenesBinding

@Route(path = ARouterPath.Function.ANIMATION_SCENES)
class ScenesActivity : BaseVbActivity<FunctionActivityAnimationScenesBinding>() {
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("ScenesActivity")
        .build()

    override fun initView() {
        val scencesAdapter = ScencesAdapter()
        mViewBinding.rvMain.apply {
            layoutManager= GridLayoutManager(this@ScenesActivity,2)
            addItemDecoration(GridSpacingItemDecoration(2, 10.dp2px, false))
            adapter = scencesAdapter
        }
    }
}