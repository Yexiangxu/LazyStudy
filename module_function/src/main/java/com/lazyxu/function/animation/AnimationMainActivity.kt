package com.lazyxu.function.animation

import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.adapter.MainAdapter
import com.lazyxu.function.databinding.FunctionActivityMainBinding


@Route(path = ARouterPath.Function.ANIMATION_MAIN)
class AnimationMainActivity : BaseVbActivity<FunctionActivityMainBinding>() {

    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("动画主页面")
        .build()


    override fun initView() {
        mList = mutableListOf(
            "帧动画",
            "补间动画",
            "属性动画",
            "自定义动画",
            "animateLayoutChanges与LayoutTransition",
            "Transition库",
            "ConstraintLayout动画",
            "触摸反馈动画",
            "共享元素",
            "CoordinatorLayout等滑动吸附效果",
            "自定义Behavior"
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

                3 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_CUSTOM)
                }

                4 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_LAYOUT)
                }

                5 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_TRANSITION)
                }

                6 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_CONSTRAINTLAYOUT_MAIN)
                }

                7 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_TOUCHFEEDBACK)
                }

                8 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_SCENES)
                }

                9 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_REVEAL)
                }
                10 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_BEHAVIOR)
                }
            }
        }
    }

}