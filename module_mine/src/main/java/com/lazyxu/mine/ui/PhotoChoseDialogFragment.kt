package com.lazyxu.mine.ui

import android.view.Gravity
import com.lazyxu.base.base.dialog.BaseVbDialogFragment
import com.lazyxu.base.ext.setClipViewCornerTopRadius
import com.lazyxu.mine.databinding.DialogPhotoChoseBinding

class PhotoChoseDialogFragment :
    BaseVbDialogFragment<DialogPhotoChoseBinding>() {
    override fun gravity(): Int {
        return Gravity.BOTTOM
    }

    override fun initView() {
        mViewBinding.llRoot.setClipViewCornerTopRadius(6)
        mViewBinding.tvCancel.setOnClickListener { dismissAllowingStateLoss() }

    }
}

