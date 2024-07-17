package com.lazyxu.base.base.head

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.lazyxu.base.R

class HeadToolbar(build: Builder) {
    @get:JvmName("toolbarTitle")
    val toolbarTitle = build.toolbarTitle

    @get:JvmName("toolbarTitleSize")
    val toolbarTitleSize = build.toolbarTitleSize

    @get:JvmName("toolbarTitleColor")
    val toolbarTitleColor = build.toolbarTitleColor

    @get:JvmName("toolbarBgColor")
    val toolbarBgColor = build.toolbarBgColor

    @get:JvmName("backDrawable")
    val backDrawable = build.backDrawable

    @get:JvmName("backClick")
    val backClick = build.backClick

    @get:JvmName("statusBarColor")
    val statusBarColor = build.statusBarColor

    @get:JvmName("isHideBack")
    val isHideBack = build.isHideBack


    class Builder : IHeadBuilder {
        internal var toolbarTitle: Any? = null
        internal var toolbarTitleSize = -1
        internal var toolbarTitleColor = -1
        internal var toolbarBgColor = -1
        internal var backDrawable = -1
        internal var backClick: (() -> Unit)? = null
        internal var statusBarColor = R.color.colorAccent
        internal var isHideBack = false


        override fun toolbarTitle(toolbarTitle: Any) = apply {
            this.toolbarTitle = toolbarTitle
        }

        override fun toolbarTitleSize(@DimenRes textSize: Int) = apply {
            this.toolbarTitleSize = textSize
        }

        override fun toolbarTitleColor(@ColorRes toolbarTitleColor: Int) = apply {
            this.toolbarTitleColor = toolbarTitleColor
        }

        override fun toolbarBgColor(@ColorRes toolbarBgColor: Int) = apply {
            this.toolbarBgColor = toolbarBgColor
        }

        override fun backDrawable(@DrawableRes backDrawable: Int) = apply {
            this.backDrawable = backDrawable
        }

        override fun backClick(backClick: () -> Unit): IHeadBuilder = apply {
            this.backClick = backClick
        }

        override fun statusBarColor(@ColorRes statusBarColor: Int) = apply {
            this.statusBarColor = statusBarColor
        }

        override fun hideBack(hideBack: Boolean) = apply {
            this.isHideBack = hideBack
        }

        override fun build(): HeadToolbar {
            return HeadToolbar(this)
        }
    }
}