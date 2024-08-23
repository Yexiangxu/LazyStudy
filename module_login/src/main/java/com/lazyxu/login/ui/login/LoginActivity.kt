package com.lazyxu.login.ui.login

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbVmActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.SystemSettingUtils
import com.lazyxu.login.R
import com.lazyxu.login.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * TODO 验证码倒计时，页面关闭不中断，杀掉进程也不中断
 * https://juejin.cn/post/6997602350856667143
 */
@Route(path = ARouterPath.User.LOGIN)
class LoginActivity : BaseVbVmActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun createObserver() {
    }

    override fun headToolbar() = HeadToolbar.Builder().toolbarTitle(R.string.login).build()

    override fun initView() {

    }


    override fun initClicks() {
        mViewBinding.btnLogin.setOnClickListener {
//            mViewModel.login(mViewBinding.etPhone.toString().trim(),mViewBinding.etPassword.toString().trim())
//            ARouterHelper.goActivity(ARouterPath.Mine.SETTING)
            var hour = 6
            var min = 7
            hour = hour + 1
            min = min + 1
            SystemSettingUtils.setSystemAlarmClock(this, "听圣经", hour, min)
        }
    }

    override fun finishAfterTransition() {
        super.finishAfterTransition()
        overridePendingTransition(0, com.lazyxu.base.R.anim.slide_bottom_out)
    }
}