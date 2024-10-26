package com.lazyxu.function.animation.transition

import android.content.Intent
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.decoration.GridSpacingItemDecoration
import com.lazyxu.function.R
import com.lazyxu.function.adapter.ScencesAdapter
import com.lazyxu.function.databinding.FunctionActivityAnimationScenesBinding
import com.lazyxu.lib_common.constant.Constants
import de.hdodenhof.circleimageview.CircleImageView

@Route(path = ARouterPath.Function.ANIMATION_SCENES)
class ScenesActivity : BaseVbActivity<FunctionActivityAnimationScenesBinding>() {
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle("ScenesActivity")
        .build()

    override fun initView() {
        val scencesAdapter = ScencesAdapter()
        mViewBinding.rvMain.apply {
            layoutManager = GridLayoutManager(this@ScenesActivity, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 10.dp2px, false))
            adapter = scencesAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            val position = data.getIntExtra(Constants.KEY_GALLERY_BACK_POS, 0)
            val viewHolder = mViewBinding.rvMain.findViewHolderForAdapterPosition(position)
            viewHolder?.let {
                val imageView = it.itemView.findViewById<AppCompatImageView>(R.id.imageview)
                val header = it.itemView.findViewById<CircleImageView>(R.id.header)
                val tvInfo = it.itemView.findViewById<AppCompatTextView>(R.id.tv_info)
                ViewCompat.setTransitionName(
                    imageView,
                    "${getString(R.string.share_element_imageview)}_${position}"
                )
                ViewCompat.setTransitionName(
                    header,
                    "${getString(R.string.share_element_header)}_${position}"
                )
                ViewCompat.setTransitionName(
                    tvInfo,
                    "${getString(R.string.share_element_tv_info)}_${position}"
                )
            }
            LogUtils.d("position=$position")
            mViewBinding.rvMain.scrollToPosition(position)
            recyclerViewShareElement(mViewBinding.rvMain, position)
        }
    }

    private fun recyclerViewShareElement(recyclerView: RecyclerView, position: Int) {
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
}