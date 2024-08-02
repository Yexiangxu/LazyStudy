package com.lazyxu.test.custom

import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.ActivityCustomview1Binding

@Route(path = ARouterPath.Function.CUSTOMVIEW1)
class CustomView1Activity :BaseVbActivity<ActivityCustomview1Binding>(){
    override fun initView() {

    }
}