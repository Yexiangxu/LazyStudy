package com.lazyxu.mine.ui

import android.Manifest
import android.view.Gravity
import androidx.lifecycle.lifecycleScope
import com.lazyxu.base.base.dialog.BaseVbDialogFragment
import com.lazyxu.base.ext.setClipViewCornerTopRadius
import com.lazyxu.base.utils.permission.RxPermissions
import com.lazyxu.mine.databinding.DialogPhotoChoseBinding
import kotlinx.coroutines.launch

class PhotoChoseDialogFragment(var openCallback: (() -> Unit)) :
    BaseVbDialogFragment<DialogPhotoChoseBinding>() {
    override fun gravity(): Int {
        return Gravity.BOTTOM
    }

    override fun initView() {
        mViewBinding.llRoot.setClipViewCornerTopRadius(6)
        mViewBinding.tvCancel.setOnClickListener { dismissAllowingStateLoss() }
        mViewBinding.tvPhoto.setOnClickListener {
            openCallback.invoke()
            dismissAllowingStateLoss()
        }
        mViewBinding.tvCamera.setOnClickListener {


        }
    }
}

