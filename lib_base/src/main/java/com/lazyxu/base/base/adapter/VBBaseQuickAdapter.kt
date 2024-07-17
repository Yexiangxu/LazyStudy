package com.lazyxu.base.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Adapter基类
 */
abstract class VBBaseQuickAdapter<T, VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
    layoutResId: Int = 0
) : BaseQuickAdapter<T, VBBaseQuickAdapter.VBBaseViewHolder<VB>>(layoutResId) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VBBaseViewHolder<VB> {
        val viewBinding = inflate(LayoutInflater.from(parent.context), parent, false)
        return VBBaseViewHolder(viewBinding)
    }

    class VBBaseViewHolder<VB : ViewBinding>(val binding: VB) : BaseViewHolder(binding.root)

}
