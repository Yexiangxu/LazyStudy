package com.lazyxu.lazystudy

import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.ext.setOnNoDoubleClickListener
import com.lazyxu.lazystudy.databinding.FragmentHomeBinding


class HomeFragment : BaseVbFragment<FragmentHomeBinding>() {
    override fun initView() {

    }

    override fun initClicks() {
        mViewBinding.tvSearch.setOnNoDoubleClickListener {
            ARouterHelper.goActivity(ARouterPath.Search.SEARCH)
        }
    }

}