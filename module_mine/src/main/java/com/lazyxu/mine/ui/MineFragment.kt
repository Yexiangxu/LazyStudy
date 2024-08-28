package com.lazyxu.mine.ui

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.R
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.base.ext.load
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lib_common.UiUtils
import com.lazyxu.mine.databinding.FragmentMineBinding

@Route(path = ARouterPath.Mine.MAIN)
class MineFragment : BaseVbFragment<FragmentMineBinding>() {

    private val maxImageSize by lazy { mViewBinding.ivHead.height }
    private val minImageSize by lazy { resources.getDimensionPixelSize(R.dimen.dp_30) }
    /**
     * [mViewBinding.tbTitle.height]获取到的不是实际的 tbTitle 高度
     */
    private val tbTitleHeight by lazy { resources.getDimensionPixelSize(R.dimen.dp_48) }
    override fun initStatusbar() {
        ImmersionBar.with(this).titleBar(mViewBinding.tbTitle)
            .navigationBarColor(R.color.black).init()
    }


    private val ivMaginTopSize by lazy {  mViewBinding.ivHead.height - (tbTitleHeight - minImageSize) / 2 }

    /**
     * TODO 替换 ImmersionBar库 有问题
     * 这个 ImmersionBar.getStatusBarHeight(this@MineFragment)获取到的值肯定有问题，不在toolbar的中间
     */
    private val statusbarHeight by lazy { ImmersionBar.getStatusBarHeight(this@MineFragment) }
    private var lastVerticalOffset: Int = -1
    override fun initView() {
        mViewBinding.ivHead.load(
            "https://p9-pc-sign.douyinpic.com/tos-cn-p-0015/f5de5023134848e99a0b455b0d2a7f47~tplv-dy-resize-origshort-autoq-75:330.jpeg?biz_tag=pcweb_cover&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&sc=cover&se=false&x-expires=1996239600&x-signature=QCNywvpquxzT%2FlCUIPivcgbLnyE%3D"
        )

        mViewBinding.ablMine.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (lastVerticalOffset != verticalOffset) {
                lastVerticalOffset = verticalOffset
            } else {
                return@addOnOffsetChangedListener
            }
            val totalScrollRange = mViewBinding.ablMine.totalScrollRange
            val percentage = Math.abs(verticalOffset).toFloat() / totalScrollRange

            mViewBinding.tbTitle.setBackgroundColor(
                UiUtils.interpolateColor(
                    Color.TRANSPARENT,
                    Color.WHITE,
                    percentage
                )
            )
            mViewBinding.ivSetting.setColorFilter(
                UiUtils.interpolateColor(
                    Color.WHITE,
                    Color.BLACK,
                    percentage
                )
            )

            val newSize = maxImageSize - (percentage * (maxImageSize - minImageSize)).toInt()
            mViewBinding.ivHead.layoutParams =
                (mViewBinding.ivHead.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
                    width = newSize
                    height = newSize
                    // 设置顶部边距，使其在 Toolbar 中垂直居中
                    val maxTopMargin = (tbTitleHeight - newSize) / 2f+statusbarHeight
                    // 设置 topMargin 使 ImageView 在滚动过程中保持垂直居中
                    topMargin = (maxTopMargin + (1 - percentage) * ivMaginTopSize).toInt()
                }

            LogUtils.d(
                LogTag.ANIMATION,
                "verticalOffset=$verticalOffset，totalScrollRange=$totalScrollRange,percentage=$percentage"
            )
        }
    }


    override fun initClicks() {
        mViewBinding.ivHead.setOnClickListener {
            ARouterHelper.goActivity(ARouterPath.User.LOGIN)
        }
        mViewBinding.ivSetting.setOnClickListener {
            ARouterHelper.goActivity(ARouterPath.Mine.SETTING)
        }
    }


}