package com.lazyxu.lazystudy

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class HomeTopAdapter : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_topmain) {

    override fun convert(holder: BaseViewHolder, item: Int) {
        val imageView = holder.getView<ImageView>(R.id.ivMainTop)

//        Glide.with(context).load(item).error(R.drawable.default_home2).into(imageView)
    }
}