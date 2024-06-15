package com.lazyxu.base.base.dialog

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lazyxu.base.R


interface DialogCallback<VB : ViewBinding> {
    fun bindTheme(): Int = R.style.DialogCommon
    fun getViewBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): VB
    fun initView(dialogfragment: BaseVbFastDialogFragment<VB>)
    fun gravityType(): Int = Gravity.CENTER
}