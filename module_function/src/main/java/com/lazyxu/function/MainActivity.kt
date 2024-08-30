package com.lazyxu.function

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.adapter.MainAdapter
import com.lazyxu.function.databinding.FunctionActivityMainBinding


@Route(path = ARouterPath.Function.MAIN)
class MainActivity : BaseVbActivity<FunctionActivityMainBinding>() {

    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.function)
        .build()


    override fun initView() {
        mList = mutableListOf(
            getString(R.string.function_dragrecyclerview),
            getString(R.string.function_deleterecyclerview),
            getString(R.string.function_dispatch),
            "自定义view",
            "Android动画特效"
        )
        mAdapter = MainAdapter(this, mList)
        mViewBinding.rvMain.adapter = mAdapter
    }


    override fun initClicks() {
        mAdapter.setOnItemClickListener {
            when (it) {
                0 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.DRAGRECYCLERVIEW)
                }

                1 -> {
                    ARouterHelper.goActivity(ARouterPath.Function.DELETEECYCLERVIEW)
                }

                2 -> {
//                    ARouterHelper.goActivity(ARouterPath.Function.DISPATCH)
                }

                3 -> ARouterHelper.goActivity(ARouterPath.Function.CUSTOMVIEW1)
                4 -> ARouterHelper.goActivity(ARouterPath.Function.ANIMATION_MAIN)
            }
        }
    }

}