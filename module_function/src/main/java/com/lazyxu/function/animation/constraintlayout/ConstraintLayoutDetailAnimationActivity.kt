package com.lazyxu.function.animation.constraintlayout

import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.function.databinding.FunctionActivityAnimationConstraintlayoutDetailBinding


@Route(path = ARouterPath.Function.ANIMATION_CONSTRAINTLAYOUT_DETAIL)
class ConstraintLayoutDetailAnimationActivity :
    BaseVbActivity<FunctionActivityAnimationConstraintlayoutDetailBinding>() {
    private val applyConstraintSet = ConstraintSet()
    private val resetConstraintSet = ConstraintSet()
    private var position = 0
    override fun initView() {
        position = intent.getIntExtra("KEY", 0)
        resetConstraintSet.clone(mViewBinding.main)
        applyConstraintSet.clone(mViewBinding.main)
    }

    fun onApplyClick(view: View) {
        when (position) {
            1 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.clear(mViewBinding.button1.id, ConstraintSet.TOP)
                applyConstraintSet.applyTo(mViewBinding.main)
            }

            2 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.clear(mViewBinding.button1.id, ConstraintSet.START)
                applyConstraintSet.clear(mViewBinding.button1.id, ConstraintSet.END)
                applyConstraintSet.clear(mViewBinding.button2.id, ConstraintSet.START)
                applyConstraintSet.clear(mViewBinding.button2.id, ConstraintSet.END)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.START)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.END)
                applyConstraintSet.centerHorizontally(
                    mViewBinding.button1.id,
                    ConstraintSet.PARENT_ID
                )
                applyConstraintSet.centerHorizontally(
                    mViewBinding.button2.id,
                    ConstraintSet.PARENT_ID
                )
                applyConstraintSet.centerHorizontally(
                    mViewBinding.button3.id,
                    ConstraintSet.PARENT_ID
                )
                applyConstraintSet.applyTo(mViewBinding.main)
            }

            3 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.START)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.END)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.TOP)
                applyConstraintSet.clear(mViewBinding.button3.id, ConstraintSet.BOTTOM)
                applyConstraintSet.centerHorizontally(
                    mViewBinding.button3.id,
                    ConstraintSet.PARENT_ID
                )
                applyConstraintSet.centerVertically(
                    mViewBinding.button3.id,
                    ConstraintSet.PARENT_ID
                )
                applyConstraintSet.applyTo(mViewBinding.main)
            }

            4 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.constrainWidth(
                    mViewBinding.button1.id,
                    600
                ) //设置宽，高度设置方法constrainHeight
                applyConstraintSet.applyTo(mViewBinding.main)
            }

            5 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.setVisibility(mViewBinding.button2.id, ConstraintSet.GONE)
                applyConstraintSet.setVisibility(mViewBinding.button3.id, ConstraintSet.GONE)
                applyConstraintSet.clear(mViewBinding.button1.id) //把 view 上的所有 constraint 都清除掉
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    0
                )
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    0
                )
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    0
                )
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    0
                )
                applyConstraintSet.applyTo(mViewBinding.main)
            }

            6 -> {
                TransitionManager.beginDelayedTransition(mViewBinding.main)
                applyConstraintSet.clear(mViewBinding.button1.id) //清除所有的约束(包括宽高、边距，全部设置为0)
                applyConstraintSet.clear(mViewBinding.button2.id)
                applyConstraintSet.clear(mViewBinding.button3.id)
                // button 1 left and top align to parent
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    0
                )
                // button 3 right and top align to parent
                applyConstraintSet.connect(
                    mViewBinding.button3.id,
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    0
                )
                // bi-direction or Chaining between button 1 and button 2
                applyConstraintSet.connect(
                    mViewBinding.button2.id,
                    ConstraintSet.LEFT,
                    mViewBinding.button1.id,
                    ConstraintSet.RIGHT,
                    0
                )
                applyConstraintSet.connect(
                    mViewBinding.button1.id,
                    ConstraintSet.RIGHT,
                    mViewBinding.button2.id,
                    ConstraintSet.LEFT,
                    0
                )
                // bi-direction or Chaining between button 2 and button 3
                applyConstraintSet.connect(
                    mViewBinding.button2.id,
                    ConstraintSet.RIGHT,
                    mViewBinding.button3.id,
                    ConstraintSet.LEFT,
                    0
                )
                applyConstraintSet.connect(
                    mViewBinding.button3.id,
                    ConstraintSet.LEFT,
                    mViewBinding.button2.id,
                    ConstraintSet.RIGHT,
                    0
                )
                applyConstraintSet.createHorizontalChain(
                    mViewBinding.button1.id,
                    ConstraintSet.LEFT,
                    mViewBinding.button3.id,
                    ConstraintSet.RIGHT,
                    intArrayOf(
                        mViewBinding.button1.id,
                        mViewBinding.button2.id,
                        mViewBinding.button3.id
                    ),
                    null,
                    ConstraintSet.CHAIN_PACKED
                )
                applyConstraintSet.constrainWidth(
                    mViewBinding.button1.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.constrainWidth(
                    mViewBinding.button2.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.constrainWidth(
                    mViewBinding.button3.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.constrainHeight(
                    mViewBinding.button1.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.constrainHeight(
                    mViewBinding.button2.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.constrainHeight(
                    mViewBinding.button3.id,
                    ConstraintSet.WRAP_CONTENT
                )
                applyConstraintSet.applyTo(mViewBinding.main)
            }
        }
    }

    fun onResetClick(view: View?) {
        TransitionManager.beginDelayedTransition(mViewBinding.main)
        resetConstraintSet.applyTo(mViewBinding.main)
    }
}