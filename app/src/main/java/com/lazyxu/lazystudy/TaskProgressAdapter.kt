package com.lazyxu.lazystudy

import android.graphics.Color
import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.lazystudy.databinding.ItemTaskProgressBinding


class TaskProgressAdapter :
    VBBaseQuickAdapter<ShortPlayYouXuanTask, ItemTaskProgressBinding>(ItemTaskProgressBinding::inflate) {
    private var receiveMins: Int = 0


    override fun convert(
        holder: VBBaseViewHolder<ItemTaskProgressBinding>,
        item: ShortPlayYouXuanTask
    ) {
        holder.binding.tvTime.text = "${item.mins}分钟"
        holder.binding.tvCoin.text = item.coins.toString()
        if (item.mins > receiveMins) {
            holder.binding.tvTime.isSelected = false
        } else {
            holder.binding.tvTime.isSelected = true
        }
    }

    fun setChosePosition(receivemins: Int) {
        this.receiveMins = receivemins
        notifyDataSetChanged()
    }
}