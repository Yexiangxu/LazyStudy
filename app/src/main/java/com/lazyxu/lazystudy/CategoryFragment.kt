package com.lazyxu.lazystudy

import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.lazystudy.databinding.FragmentCategoryBinding


class CategoryFragment : BaseVbFragment<FragmentCategoryBinding>() {

    override fun initView() {

        mViewBinding.tvHotEffect.setOnClickListener {
            ARouterHelper.goActivity(ARouterPath.Search.SEARCH)
        }
    }
}