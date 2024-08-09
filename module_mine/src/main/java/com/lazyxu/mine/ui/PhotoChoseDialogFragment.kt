package com.lazyxu.mine.ui

import android.Manifest
import android.view.Gravity
import com.lazyxu.base.base.dialog.BaseVbDialogFragment
import com.lazyxu.base.ext.setClipViewCornerTopRadius
import com.lazyxu.base.utils.permission.RxPermissions
import com.lazyxu.mine.databinding.DialogPhotoChoseBinding

class PhotoChoseDialogFragment :
    BaseVbDialogFragment<DialogPhotoChoseBinding>() {
    override fun gravity(): Int {
        return Gravity.BOTTOM
    }

    override fun initView() {
        mViewBinding.llRoot.setClipViewCornerTopRadius(6)
        mViewBinding.tvCancel.setOnClickListener { dismissAllowingStateLoss() }
        mViewBinding.tvCamera.setOnClickListener {
            RxPermissions(fragment = this@PhotoChoseDialogFragment)
                .request(Manifest.permission.CAMERA)
                .collect { granted ->
                    if (granted) {

                    } else {
                    }
                }
        }

    }
}

