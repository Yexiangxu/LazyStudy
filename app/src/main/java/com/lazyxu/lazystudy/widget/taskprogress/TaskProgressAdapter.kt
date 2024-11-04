package com.lazyxu.lazystudy.widget.taskprogress

import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.lazystudy.ShortPlayYouXuanTask
import com.lazyxu.lazystudy.databinding.ItemGalleryBinding
import com.lazyxu.lazystudy.databinding.ItemTaskProgressBinding


class TaskProgressAdapter :
    VBBaseQuickAdapter<ShortPlayYouXuanTask, ItemTaskProgressBinding>(ItemTaskProgressBinding::inflate) {
    private var receiveMins: Int = 0


    override fun onBindViewHolder(
        holder: VH<ItemTaskProgressBinding>,
        position: Int,
        item: ShortPlayYouXuanTask?
    ) {
        holder.binding.tvTime.text = "${item?.mins}分钟"
        holder.binding.tvCoin.text = item?.coins.toString()
        holder.binding.tvTime.isSelected = item?.mins!! <= receiveMins
    }

    fun setChosePosition(receivemins: Int) {
        this.receiveMins = receivemins
        notifyDataSetChanged()
    }
}