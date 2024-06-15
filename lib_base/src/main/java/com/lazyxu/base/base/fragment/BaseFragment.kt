package com.lazyxu.base.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.R
import com.lazyxu.base.log.LogUtils

/**
 * User:Lazy_xu
 * Date:2024/05/14
 * Description:
 * FIXME
 */
abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initContentView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d("${javaClass.simpleName} onViewCreated")
        initView()
        initData()
        initClicks()
    }

    protected abstract fun initContentView(inflater: LayoutInflater, container: ViewGroup?): View

    /**
     * 初始化view
     */
    abstract fun initView()
    open fun initData() {}

    /**
     * 处理所有点击事件
     */
    open fun initClicks() {}


    /**
     * 设置撑满状态栏
     */
    open fun initStatusbar() {
        ImmersionBar.with(this)
            .autoDarkModeEnable(true)
            .statusBarDarkFont(true, 0.2f)
            .navigationBarColor(R.color.black)
            .navigationBarDarkIcon(true)
            .keyboardEnable(false)
            .init()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d("${javaClass.simpleName} onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("${javaClass.simpleName} onCreate")
    }


    override fun onStart() {
        super.onStart()
        LogUtils.d("${javaClass.simpleName} onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("${javaClass.simpleName} onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("${javaClass.simpleName} onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("${javaClass.simpleName} onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("${javaClass.simpleName} onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("${javaClass.simpleName} onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d("${javaClass.simpleName} onDetach")
    }
}