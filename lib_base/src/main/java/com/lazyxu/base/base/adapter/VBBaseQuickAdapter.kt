package com.lazyxu.base.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter4.BaseQuickAdapter

/**
 * Adapter基类
 */
abstract class VBBaseQuickAdapter<T : Any, VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB
) : BaseQuickAdapter<T, VBBaseQuickAdapter.VH<VB>>() {
    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH<VB> {
        return VH(inflate(LayoutInflater.from(context), parent, false))

    }

    class VH<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}
