package com.lazyxu.animation

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.R
import com.lazyxu.function.adapter.MainAdapter
import com.lazyxu.function.databinding.FunctionActivityMainBinding


@Route(path = ARouterPath.Function.ANIMATION_MAIN)
class AnimationMainActivity : BaseVbActivity<FunctionActivityMainBinding>() {

    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.function)
        .build()


    override fun initView() {
        mList = mutableListOf(

            "帧动画",
            "补间动画",
            "属性动画"
        )
        mAdapter = MainAdapter(this, mList)
        mViewBinding.rvMain.adapter = mAdapter
    }


    override fun initClicks() {
        mAdapter.setOnItemClickListener {
            when (it) {
                0 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_FRAME)
                }

                1 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_TWEEN)
                }

                2 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_PROPERTY)
                }
            }
        }
    }

}