package com.lazyxu.function.animation.transition

import android.content.Intent
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.layoutmanager.PagerLayoutManager
import com.lazyxu.function.R
import com.lazyxu.function.adapter.ScencesDetailAdapter
import com.lazyxu.function.databinding.FunctionActivityAnimationShareelementsBinding
import com.lazyxu.lib_common.constant.Constants
import de.hdodenhof.circleimageview.CircleImageView


@Route(path = ARouterPath.Function.ANIMATION_SHAREELEMENTS)
class ShareElementsActivity : BaseVbActivity<FunctionActivityAnimationShareelementsBinding>() {
    private val scencesDetailAdapter by lazy {
        ScencesDetailAdapter()
    }

    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("元素共享动画")
        .build()

    private var position = 0

    override fun initView() {
        position = intent.getIntExtra(Constants.KEY_VIDEO_PLAY_POS, 0)
        val manager =
            PagerLayoutManager(this@ShareElementsActivity, LinearLayoutManager.HORIZONTAL, false)
        mViewBinding.rvGallery.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = scencesDetailAdapter
        }
        mViewBinding.rvGallery.scrollToPosition(position)
        recyclerViewShareElement(mViewBinding.rvGallery)
    }

    /**
     * 跳转详情页是使用recyclerview，在recyclerview的item中setTransitionName使用过渡动画需要有该设置才生效
     */
    private fun recyclerViewShareElement(recyclerView: RecyclerView) {
        // 设置延迟共享元素过渡动画直到 RecyclerView 滚动完成
        postponeEnterTransition()
        recyclerView.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    recyclerView.viewTreeObserver.removeOnPreDrawListener(this)
                    // 开始过渡动画
                    startPostponedEnterTransition()
                    return true
                }
            })
    }

    override fun onBackPressed() {
        val layoutManager = mViewBinding.rvGallery.layoutManager as LinearLayoutManager
        val currentPosition = layoutManager.findFirstVisibleItemPosition()
        val viewHolder = mViewBinding.rvGallery.findViewHolderForAdapterPosition(currentPosition)
        viewHolder?.let {
            val imageview1 = it.itemView.findViewById<AppCompatImageView>(R.id.imageview1)
            val header1 = it.itemView.findViewById<CircleImageView>(R.id.header1)
            val tv_info1 = it.itemView.findViewById<AppCompatTextView>(R.id.tv_info1)
            ViewCompat.setTransitionName(
                imageview1,
                "${getString(R.string.share_element_imageview)}_${currentPosition}"
            )
            ViewCompat.setTransitionName(
                header1,
                "${getString(R.string.share_element_header)}_${currentPosition}"
            )
            ViewCompat.setTransitionName(
                tv_info1,
                "${getString(R.string.share_element_tv_info)}_${currentPosition}"
            )
        }
        LogUtils.d(LogTag.ANIMATION, "currentPosition=$currentPosition,viewHolder=${viewHolder}")
        setResult(RESULT_OK, Intent().apply {
            putExtra(Constants.KEY_GALLERY_BACK_POS, currentPosition)
        })
        supportFinishAfterTransition()
    }
}