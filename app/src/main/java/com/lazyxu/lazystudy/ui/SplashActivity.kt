package com.lazyxu.lazystudy.ui

import android.view.KeyEvent
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.ext.countDownCoroutines
import com.lazyxu.base.ext.setOnNoDoubleClickListener
import com.lazyxu.base.arouter.ARouterHelper.goActivityFinishCurrent
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.lazystudy.R
import com.lazyxu.lazystudy.databinding.ActivitySplashBinding


/**
 * 可放置开屏广告
 */
class SplashActivity : BaseVbActivity<ActivitySplashBinding>() {
    companion object {
        private const val DEFAULT_SHOW_TIME = 5
    }

    override fun initClicks() {
        mViewBinding.btnSkip.setOnNoDoubleClickListener {
            jumpToMain()
        }
    }


    override fun initView() {
        countDownCoroutines(DEFAULT_SHOW_TIME,lifecycleScope, onTick = {
            mViewBinding.btnSkip.text = getString(R.string.splash_skip, it)
        }, onFinish = {
            jumpToMain()
        })
    }

    override fun initStatusbar() {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)//解决布局顶部和状态栏重叠问题
            .hideBar(BarHide.FLAG_HIDE_BAR)
            .init()
    }

    private fun jumpToMain() {
        this.goActivityFinishCurrent(ARouterPath.MAIN)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            true
        } else super.onKeyDown(keyCode, event)
    }
}