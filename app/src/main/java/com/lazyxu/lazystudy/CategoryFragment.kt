package com.lazyxu.lazystudy

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Interpolator
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.base.ext.dp2px
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
            ARouterHelper.goActivity(ARouterPath.Function.MAIN)
        }
    }


}