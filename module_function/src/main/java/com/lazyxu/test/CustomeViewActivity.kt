package com.lazyxu.test

import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.function.databinding.ActivityTestCustomeViewBinding

class CustomeViewActivity : BaseVbActivity<ActivityTestCustomeViewBinding>() {
    override fun headToolbar(): HeadToolbar = HeadToolbar.Builder()
        .toolbarTitle("自定义view")
        .build()

    override fun initView() {
    }

    override fun initClicks() {
    }


}