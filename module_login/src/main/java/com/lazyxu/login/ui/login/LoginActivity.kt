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
    lateinit var mRightOutSet: AnimatorSet
    lateinit var mLeftInSet: AnimatorSet
    override fun headToolbar() = HeadToolbar.Builder().toolbarTitle(R.string.login).build()

    @SuppressLint("ResourceType")
    override fun initView() {
        isPackageExist(this, this.packageName)

    }

    fun isPackageExist(context: Context, packageName: String): Boolean {
        LogUtils.d("packageName=$packageName")
        if (TextUtils.isEmpty(packageName)) return false
        var isExist = false
        try {
//            isExist = null != context.packageManager.getApplicationInfo(packageName, MATCH_UNINSTALLED_PACKAGES) && null != context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
        } finally {
            return isExist
        }
    }

    override fun initClicks() {


        var hour = 6
        var min = 7
        mViewBinding.btnLogin.setOnClickListener {
//            mViewModel.login(mViewBinding.etPhone.toString().trim(),mViewBinding.etPassword.toString().trim())
//            ARouterHelper.goActivity(ARouterPath.Mine.SETTING)

            hour = hour + 1
            min = min + 1
            SystemSettingUtils.setSystemAlarmClock(this, "听圣经", hour, min)
        }
    }


    override fun createObserver() {
        mViewModel.loginLiveData.observe(this) {

        }

    }

}