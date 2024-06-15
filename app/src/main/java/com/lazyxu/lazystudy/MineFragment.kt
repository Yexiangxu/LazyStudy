package com.lazyxu.lazystudy

import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.lazystudy.databinding.FragmentMineBinding

class MineFragment : BaseVbFragment<FragmentMineBinding>() {
    override fun initView() {

    }

    override fun initClicks() {
        mViewBinding.tvHotEffect.setOnClickListener { ARouterHelper.goActivity(ARouterPath.User.LOGIN) }
    }
}