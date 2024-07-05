package com.lazyxu.lazystudy

import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.base.ext.load
import com.lazyxu.lazystudy.databinding.ItemGalleryBinding
import com.lazyxu.lib_database.entity.VideoEntity

class GalleryAdapter :
    VBBaseQuickAdapter<VideoEntity, ItemGalleryBinding>(ItemGalleryBinding::inflate) {
    override fun convert(holder: VBBaseViewHolder<ItemGalleryBinding>, item: VideoEntity) {
        holder.binding.ivBg.load(item.image_url)
        holder.binding.tvTitle.text = item.title
    }
}