package com.lazyxu.function.animation.constraintlayout

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.FunctionActivityAnimationConstraintlayoutMainBinding

@Route(path = ARouterPath.Function.ANIMATION_CONSTRAINTLAYOUT_MAIN)
class ConstraintLayoutMainAnimationActivity :
    BaseVbActivity<FunctionActivityAnimationConstraintlayoutMainBinding>() {
    override fun initView() {
        mViewBinding.btnConstrain1.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 1)
            startActivity(intent)
        }
        mViewBinding.btnConstrain2.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 2)
            startActivity(intent)
        }
        mViewBinding.btnConstrain3.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 3)
            startActivity(intent)
        }
        mViewBinding.btnConstrain4.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 4)
            startActivity(intent)
        }
        mViewBinding.btnConstrain5.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 5)
            startActivity(intent)
        }
        mViewBinding.btnConstrain6.setOnClickListener {
            val intent: Intent =
                Intent(this, ConstraintLayoutDetailAnimationActivity::class.java)
            intent.putExtra("KEY", 6)
            startActivity(intent)
        }
    }
}
