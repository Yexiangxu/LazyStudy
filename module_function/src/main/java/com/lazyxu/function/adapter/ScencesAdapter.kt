package com.lazyxu.function.adapter

import android.app.Activity
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.adapter.BaseAdapter
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionItemShareElementsBinding
import com.lazyxu.function.entity.ShareElementsEntity

/**
 * User:Lazy_xu
 * Date:2024/02/21
 * Description:
 * FIXME
 */
class ScencesAdapter(shareElementsEntity: List<ShareElementsEntity>? = null) :
    BaseAdapter<FunctionItemShareElementsBinding, ShareElementsEntity>(shareElementsEntity) {
    override fun getItemCount(): Int {
        return 10
    }


    override fun onBind(
        binding: FunctionItemShareElementsBinding,
        position: Int,
        item: ShareElementsEntity?
    ) {
        binding.root.setOnClickListener {
            val pair1 = androidx.core.util.Pair<View, String>(
                binding.imageview,
                binding.root.context.getString(R.string.share_element_imageview)
            )
            val pair2 = androidx.core.util.Pair<View, String>(
                binding.header,
                binding.root.context.getString(R.string.share_element_header)
            )
            val pair3 = androidx.core.util.Pair<View, String>(
                binding.tvInfo,
                binding.root.context.getString(R.string.share_element_tv_info)
            )
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (binding.root.context as Activity),
                pair1,
                pair2,
                pair3
            )
            ARouterHelper.goActivity(
                ARouterPath.Function.ANIMATION_SHAREELEMENTS,
                options = activityOptionsCompat,
                activity = binding.root.context as Activity
            )
        }

    }
}