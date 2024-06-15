package com.lazyxu.network.entity.base

/**
 * 处理分页
 * 去除魔法值
 */
class PageInfo {
    companion object {
        const val PAGEBEGIN = 0
    }

    var page = PAGEBEGIN//该项目第一页是从0开始
    fun nextPage() {
        page++
    }

    fun reset() {
        page = PAGEBEGIN
    }

    val isFirstPage: Boolean
        get() = page == PAGEBEGIN
}