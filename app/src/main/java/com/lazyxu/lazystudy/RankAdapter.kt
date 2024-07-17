package com.lazyxu.lazystudy

import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.base.ext.load
import com.lazyxu.lazystudy.databinding.ItemHomeRankBinding
import com.lazyxu.lib_database.entity.VideoEntity

class RankAdapter :
    VBBaseQuickAdapter<VideoEntity, ItemHomeRankBinding>(ItemHomeRankBinding::inflate) {
    override fun convert(holder: VBBaseViewHolder<ItemHomeRankBinding>, item: VideoEntity) {
        holder.binding.ivBg.load(item.image_url)
        holder.binding.tvTitle.text=item.title
    }
}