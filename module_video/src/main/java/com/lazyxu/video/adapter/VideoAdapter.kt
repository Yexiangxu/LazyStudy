package com.lazyxu.video.adapter

import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lib_database.entity.VideoEntity
import com.lazyxu.video.databinding.ItemVideoPlayBinding

class VideoAdapter :
    VBBaseQuickAdapter<VideoEntity, ItemVideoPlayBinding>(ItemVideoPlayBinding::inflate) {
    override fun onBindViewHolder(
        holder: VH<ItemVideoPlayBinding>,
        position: Int, item: VideoEntity?
    ) {
        LogUtils.d(LogTag.VIDEO, "convert===${holder.layoutPosition}")
        holder.binding.tvMsg.text = item?.desc
        holder.binding.tvAuthor.text = "@${item?.author_name}"
    }
}