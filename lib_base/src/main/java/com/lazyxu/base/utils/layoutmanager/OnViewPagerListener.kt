package com.lazyxu.base.utils.layoutmanager

import android.view.View

/**
 * itemView选中回调
 */
interface OnViewPagerListener {

    /**
     * 释放
     */
    fun onPageRelease(isNext: Boolean, position: Int, view: View?)

    /**
     * 选中
     */
    fun onPageSelected(isNext: Boolean,position: Int, view: View?)

    /**
     * 滑动到最后一个提前预加载
     */
    fun onPreLoadMore(position: Int, view: View?)
}