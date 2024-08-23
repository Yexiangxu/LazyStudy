package com.lazyxu.mine.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.base.ext.load
import com.lazyxu.mine.databinding.FragmentMineBinding

@Route(path = ARouterPath.Mine.MAIN)
class MineFragment : BaseVbFragment<FragmentMineBinding>() {
    override fun initView() {
        mViewBinding.ivHead.load(
            "https://p9-pc-sign.douyinpic.com/tos-cn-p-0015/f5de5023134848e99a0b455b0d2a7f47~tplv-dy-resize-origshort-autoq-75:330.jpeg?biz_tag=pcweb_cover&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&sc=cover&se=false&x-expires=1996239600&x-signature=QCNywvpquxzT%2FlCUIPivcgbLnyE%3D"
        )

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