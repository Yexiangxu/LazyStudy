package com.lazyxu.lazystudy

import androidx.core.view.ViewCompat
import com.lazyxu.base.base.adapter.VBBaseQuickAdapter
import com.lazyxu.base.ext.load
import com.lazyxu.lazystudy.databinding.ItemGalleryBinding
import com.lazyxu.lib_database.entity.VideoEntity

class GalleryAdapter :
    VBBaseQuickAdapter<VideoEntity, ItemGalleryBinding>(ItemGalleryBinding::inflate) {
    override fun onBindViewHolder(
        holder: VH<ItemGalleryBinding>,
        position: Int,
        item: VideoEntity?
    ) {
        holder.binding.ivGallery.load(item?.image_url)
        holder.binding.tvTitle.text = item?.author_name
        ViewCompat.setTransitionName(
            holder.binding.ivGallery,
            "${context.getString(com.lazyxu.lib_common.R.string.video_share_element_imageview)}_${holder.layoutPosition}"
        )
    }
}