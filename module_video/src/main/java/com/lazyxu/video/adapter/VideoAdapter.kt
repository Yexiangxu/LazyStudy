package com.lazyxu.video.adapter

import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.lib_database.entity.VideoEntity
import com.lazyxu.video.databinding.ItemVideoPlayBinding

class VideoAdapter :VBBaseQuickAdapter<VideoEntity,ItemVideoPlayBinding>(ItemVideoPlayBinding::inflate){
    override fun convert(holder: VBBaseViewHolder<ItemVideoPlayBinding>, item: VideoEntity) {
        holder.binding.tvMsg.text=item.desc
        holder.binding.tvAuthor.text="@${item.author_name}"
    }
}