package com.lazyxu.function.animation

import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.R
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.ext.visible
import com.lazyxu.function.databinding.FunctionActivityAnimationRevealBinding
import kotlin.math.abs

@Route(path = ARouterPath.Function.ANIMATION_REVEAL)
class RevealAnimationActivity : BaseVbActivity<FunctionActivityAnimationRevealBinding>() {
    override fun initStatusbar(color: Int) {
        ImmersionBar.with(this).titleBar(mViewBinding.tbTitle)
            .navigationBarColor(R.color.black).init()
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mViewBinding.tbTitle.setTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        setSupportActionBar(mViewBinding.tbTitle)
        mViewBinding.ablReveal.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) == appBarLayout.totalScrollRange) {//折叠
                // 折叠状态
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                mViewBinding.tbTitle.visible()
            } else {  //else if (verticalOffset == 0) {// 展开状态 else {// 中间状态
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                mViewBinding.tbTitle.title = ""
            }
        })
    }
}