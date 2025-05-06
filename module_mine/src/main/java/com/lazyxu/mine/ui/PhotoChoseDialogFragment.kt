package com.lazyxu.mine.ui

import android.view.Gravity
import com.lazyxu.base.base.dialog.BaseVbDialogFragment
import com.lazyxu.base.ext.setClipViewCornerTopRadius
import com.lazyxu.mine.databinding.DialogPhotoChoseBinding

/**
 * 系统只会调用无参构造函数恢复 Fragment,使用构造函数传参（如 PhotoChoseDialogFragment(lambda)）在 恢复时会抛异常：Fragment$InstantiationException
 */
class PhotoChoseDialogFragment :
    BaseVbDialogFragment<DialogPhotoChoseBinding>() {
    override fun gravity(): Int {
        return Gravity.BOTTOM
    }

    private var openCallback: (() -> Unit)? = null
    fun setOpenCallback(callback: () -> Unit): PhotoChoseDialogFragment {
        this.openCallback = callback
        return this
    }

    override fun initView() {
        mViewBinding.llRoot.setClipViewCornerTopRadius(6)
        mViewBinding.tvCancel.setOnClickListener { dismissAllowingStateLoss() }
        mViewBinding.tvPhoto.setOnClickListener {
            openCallback?.invoke()
            dismissAllowingStateLoss()
        }
        mViewBinding.tvCamera.setOnClickListener {
        }
    }
}

