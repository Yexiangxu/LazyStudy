package com.lazyxu.lazystudy.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lazystudy.CategoryFragment
import com.lazyxu.lazystudy.HomeFragment
import com.lazyxu.lazystudy.R
import com.lazyxu.lazystudy.databinding.ActivityMainBinding
import kotlin.system.exitProcess


@Route(path = ARouterPath.MAIN)
class MainActivity : BaseVbActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mViewBinding.bnvMain.selectedItemId = R.id.menu_home
        }
    }

    override fun initView() {
        mViewBinding.bnvMain.itemIconTintList = null//设置tab图标，注意使用 app:itemIconTint="@null"设置无效
        mViewBinding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> setFragmentPosition(0)
                R.id.menu_type -> setFragmentPosition(1)
                R.id.menu_mine -> setFragmentPosition(2)
                else -> {}
            }
            true
        }
    }

    private var mFragments: MutableList<Fragment> =
        mutableListOf(
            HomeFragment(),
            CategoryFragment(),
            ARouterHelper.getFragment(ARouterPath.Mine.MAIN)
        )
    private var lastIndex = -1
    private fun setFragmentPosition(position: Int) {
        try {
            if (lastIndex == position) return
            val fragmentManager = supportFragmentManager
            val ft = fragmentManager.beginTransaction()
            val currentFragment = mFragments[position]
            //这里要用fragmentManager.fragments.filter，不能用mFragments.filter
            fragmentManager.fragments.filter { it != currentFragment && it.isAdded }
                .forEach {
                    Log.d("FragmentManager","hide=${it}")
                    ft.hide(it)
                    ft.setMaxLifecycle(it, Lifecycle.State.STARTED)
                }
            if (!currentFragment.isAdded) {
                Log.d("FragmentManager","add=${currentFragment}")
                ft.add(R.id.fragment_main, currentFragment)
            } else {
                Log.d("FragmentManager","add=${currentFragment}")
                ft.show(currentFragment)
            }
            ft.setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
            ft.commitAllowingStateLoss()
            lastIndex = position
        } catch (e: Exception) {
            LogUtils.d("setFragmentPosition=${e}")
        }
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack() // 弹出返回栈中的事务
            return
        }
        if (canClick()) {
            Toast.makeText(this, getString(R.string.exit_tips), Toast.LENGTH_SHORT).show()
        } else {
            finishAffinity(); // 退出应用
            exitProcess(0);
        }
    }

    private var mLastBackPressed = 0L

    /**
     * 防双击
     */
    private fun canClick(): Boolean {
        if (System.currentTimeMillis() - mLastBackPressed < 5000L) {
            return false
        }
        mLastBackPressed = System.currentTimeMillis()
        return true
    }
}
