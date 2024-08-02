package com.lazyxu.customview

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.FunctionActivityCustomview1Binding


@Route(path = ARouterPath.Function.CUSTOMVIEW1)
class CustomView1Activity : BaseVbActivity<FunctionActivityCustomview1Binding>() {


    override fun initView() {
        mViewBinding.viewDashboard.setCurrentValue(50)
        mViewBinding.viewPie.setPieList(
            mutableListOf(
                PieEntity(value = 35f, color = Color.parseColor("#00b086")),
                PieEntity(value = 30f, color = Color.parseColor("#AC38ED")),
                PieEntity(value = 15f, color = Color.parseColor("#3780C0")),
                PieEntity(value = 20f, color = Color.parseColor("#00D000"))
            )
        )
    }
}