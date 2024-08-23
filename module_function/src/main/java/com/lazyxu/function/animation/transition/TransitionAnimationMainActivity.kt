package com.lazyxu.function.animation.transition

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.Constants
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionAcitivityAnimationTransitionMainBinding


@Route(path = ARouterPath.Function.ANIMATION_TRANSITION)
class TransitionAnimationMainActivity :
    BaseVbActivity<FunctionAcitivityAnimationTransitionMainBinding>() {

    override fun initView() {
    }


    fun show(v: View) {
        when (v.id) {
            R.id.btn_view_gone1 -> {
                val intent1: Intent =
                    Intent(this, TransitionAnimationDetailViewActivity::class.java)
                intent1.putExtra(Constants.VIEW_KEY, Constants.VIEW_VISIBLE)
                startActivity(intent1)
            }

            R.id.btn_view_gone2 -> {
                val intent2: Intent = Intent(this, TransitionAnimationDetailViewActivity::class.java)
                intent2.putExtra(Constants.VIEW_KEY, Constants.VIEW_MOVE)
                startActivity(intent2)
            }

            R.id.btn_view_gone3 -> {
                val intent3: Intent = Intent(this, TransitionAnimationDetailViewActivity::class.java)
                intent3.putExtra(Constants.VIEW_KEY, Constants.VIEW_SLIDE)
                startActivity(intent3)
            }

            R.id.btn_view_scale -> {
                val intentScale: Intent = Intent(
                    this,
                    TransitionAnimationDetailViewActivity::class.java
                )
                intentScale.putExtra(Constants.VIEW_KEY, Constants.VIEW_SCALE)
                startActivity(intentScale)
            }

            R.id.btn_image -> startActivity(
                Intent(
                    this,
                    ImageTransformActivity::class.java
                )
            )

            R.id.btn_colorchange -> {
                val intentColor: Intent = Intent(
                    this,
                    TransitionAnimationDetailViewActivity::class.java
                )
                intentColor.putExtra(Constants.VIEW_KEY, Constants.View_RECOLOR)
                startActivity(intentColor)
            }

            R.id.btn_textchange -> {
                val textChange: Intent = Intent(
                    this,
                    TransitionAnimationDetailViewActivity::class.java
                )
                textChange.putExtra(Constants.VIEW_KEY, Constants.CHANGETEXT)
                startActivity(textChange)
            }

        }
    }


}