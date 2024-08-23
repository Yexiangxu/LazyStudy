package com.lazyxu.function.animation

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.ext.gone
import com.lazyxu.base.ext.visible
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionActivityAnimationLayoutBinding
import com.lazyxu.function.databinding.FunctionItemLayoutBinding


@Route(path = ARouterPath.Function.ANIMATION_LAYOUT)
class LayoutAnimationActivity : BaseVbActivity<FunctionActivityAnimationLayoutBinding>() {
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("LayoutAnimation")
        .build()

    override fun initView() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_open_animate -> {
                mViewBinding.container.layoutTransition = LayoutTransition()
                return true
            }

            R.id.menu_close_animate -> {
                mViewBinding.container.layoutTransition = null
                return true
            }

            R.id.menu_animatelayoutchanges_add -> {
                mViewBinding.tvTitle.gone()
                addItem()
                return true
            }

            R.id.menu_layouttransition -> {//这里不受open和close控制，只是单纯演示kotlin使用
                mViewBinding.tvTitle.gone()
                val layoutTransition = LayoutTransition()
                mViewBinding.container.layoutTransition = layoutTransition
                layoutTransition.setAnimator(LayoutTransition.APPEARING, null)
                layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, null)
                layoutTransition.addTransitionListener(object :
                    LayoutTransition.TransitionListener {
                    override fun startTransition(
                        transition: LayoutTransition?,
                        container: ViewGroup?,
                        view: View?,
                        transitionType: Int
                    ) {
                        if (transitionType == LayoutTransition.APPEARING) {
                            view?.alpha = 0f
                            view?.animate()?.alpha(1f)
                                ?.setDuration(2000)
                                ?.start()
                        } else if (transitionType == LayoutTransition.DISAPPEARING) {
                            view?.animate()?.alpha(0f)?.setDuration(2000)
                                ?.withEndAction {
                                    container?.removeView(view)
                                }?.start()
                        }
                    }

                    override fun endTransition(
                        transition: LayoutTransition?,
                        container: ViewGroup?,
                        view: View?,
                        transitionType: Int
                    ) {
                    }
                })
                addItem()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addItem() {
        val functionItemLayoutBinding =
            FunctionItemLayoutBinding.inflate(layoutInflater, mViewBinding.container, false)
        // 随机设置子View的内容
        functionItemLayoutBinding.tvItemLayout.text =
            COUNTRIES[(Math.random() * COUNTRIES.size).toInt()]
        //设置删除按钮的监听
        functionItemLayoutBinding.btnRemove.setOnClickListener {
            mViewBinding.container.removeView(functionItemLayoutBinding.root)
            if (mViewBinding.container.childCount == 0) {
                mViewBinding.tvTitle.visible()
            }
        }
        //添加子View
        mViewBinding.container.addView(functionItemLayoutBinding.root, 0)
    }

    private val COUNTRIES: Array<String> = arrayOf(
        "Belgium",
        "France",
        "Italy",
        "Germany",
        "Spain",
        "Austria",
        "Russia",
        "Poland",
        "Croatia",
        "Greece",
        "Ukraine",
    )
}