package com.lazyxu.lazystudy

import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.lazystudy.databinding.FragmentCategoryBinding


class CategoryFragment : BaseVbVmFragment<FragmentCategoryBinding, HomeViewModel>() {

    override fun createObserver() {

    }

    override fun initData() {
    }

    override fun initView() {

    }

    override fun initClicks() {
        mViewBinding.tvCustomview.setOnClickListener {
            ARouterHelper.goActivity(ARouterPath.Mine.SETTING)
//            ARouterHelper.goActivity(ARouterPath.Function.MAIN)
        }
    }


}