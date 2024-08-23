package com.lazyxu.base.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseAdapter<VB : ViewBinding, T>(
    private val items: List<T>? = null
) : RecyclerView.Adapter<BaseAdapter<VB, T>.BaseViewHolder>() {
    abstract fun onBind(binding: VB, position: Int, item: T?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val vbClass: Class<VB> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        val mViewBinding =
            method.invoke(this, LayoutInflater.from(parent.context), parent, false) as VB
        return BaseViewHolder(mViewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items?.get(position)
        onBind(holder.binding, position, item)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}
