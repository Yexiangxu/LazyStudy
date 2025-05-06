package com.lazyxu.lazystudy.ui

import android.view.KeyEvent
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.lazystudy.WxPayHelper
import com.lazyxu.base.arouter.ARouterHelper.goActivityFinishCurrent
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.ext.setOnNoDoubleClickListener
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.TimeCountDown
import com.lazyxu.lazystudy.R
import com.lazyxu.lazystudy.databinding.ActivitySplashBinding


/**
 * 可放置开屏广告
 */
class SplashActivity : BaseVbActivity<ActivitySplashBinding>() {
    companion object {
        private const val DEFAULT_SHOW_TIME = 3
    }

    override fun initClicks() {
        mViewBinding.btnSkip.setOnNoDoubleClickListener {
//            WxPayHelper().pay()
            jumpToMain()
//            timeCountDown.cancel()
        }
    }

    private var timeCountDown = TimeCountDown()
    override fun initView() {
        timeCountDown = TimeCountDown()
        LogUtils.d("AdTag", "开始倒计时")
        timeCountDown.start(DEFAULT_SHOW_TIME, lifecycleScope, onTick = {
            LogUtils.d("AdTag", "倒计时 ${it}")
            mViewBinding.btnSkip.text = getString(R.string.splash_skip, it)
        }, onFinish = {
            LogUtils.d("AdTag", "${Thread.currentThread().name}倒计时结束")
//            jumpToMain()
        })
    }

    override fun initStatusbar(color: Int) {
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