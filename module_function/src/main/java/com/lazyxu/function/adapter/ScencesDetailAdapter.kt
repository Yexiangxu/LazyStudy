package com.lazyxu.function.adapter

import androidx.core.view.ViewCompat
import com.lazyxu.base.base.adapter.BaseAdapter
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionItemShareelementsBigBinding
import com.lazyxu.function.entity.ShareElementsEntity

/**
 * User:Lazy_xu
 * Date:2024/02/21
 * Description:
 * FIXME
 */
class ScencesDetailAdapter(shareElementsEntity: List<ShareElementsEntity>? = null) :
    BaseAdapter<FunctionItemShareelementsBigBinding, ShareElementsEntity>(shareElementsEntity) {
    override fun getItemCount(): Int {
        return 10
    }


    override fun onBind(
        binding: FunctionItemShareelementsBigBinding,
        position: Int,
        item: ShareElementsEntity?
    ) {

        ViewCompat.setTransitionName(
            binding.imageview1,
            "${binding.root.context.getString(R.string.share_element_imageview)}_${position}"
        )
        ViewCompat.setTransitionName(
            binding.header1,
            "${binding.root.context.getString(R.string.share_element_header)}_${position}"
        )
        ViewCompat.setTransitionName(
            binding.tvInfo1,
            "${binding.root.context.getString(R.string.share_element_tv_info)}_${position}"
        )
    }
}