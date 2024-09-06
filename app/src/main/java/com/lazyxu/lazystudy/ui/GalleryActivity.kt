package com.lazyxu.lazystudy.ui

import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.utils.layoutmanager.PagerLayoutManager
import com.lazyxu.base.widget.ProportionalImageView
import com.lazyxu.lazystudy.GalleryAdapter
import com.lazyxu.lazystudy.R
import com.lazyxu.lazystudy.databinding.ActivityGalleryBinding
import com.lazyxu.lib_common.constant.Constants
import com.lazyxu.lib_database.entity.VideoEntity


@Route(path = ARouterPath.PIC)
class GalleryActivity : BaseVbActivity<ActivityGalleryBinding>() {
    //    @Autowired(name = Constants.KEY_VIDEO_PLAY_LIST)
//    @JvmField
    var mData: ArrayList<VideoEntity>? = null

    //    @Autowired(name = Constants.KEY_VIDEO_PLAY_POS)
//    @JvmField
    var position: Int = 0
    override fun initStatusbar(color: Int) {
        ImmersionBar.with(this)
            .titleBarMarginTop(mViewBinding.tbTitle)
            .navigationBarColor(com.lazyxu.base.R.color.black)
            .init()
    }

    private val galleryActivity by lazy {
        GalleryAdapter()
    }

    override fun initView() {
        mData = intent.getParcelableArrayListExtra(Constants.KEY_VIDEO_PLAY_LIST)
        position = intent.getIntExtra(Constants.KEY_VIDEO_PLAY_POS, 0)
        val manager =
            PagerLayoutManager(this@GalleryActivity, LinearLayoutManager.HORIZONTAL, false)
        mViewBinding.rvGallery.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = galleryActivity
        }
        galleryActivity.setList(mData)
        mViewBinding.rvGallery.scrollToPosition(position)
    }

    override fun onBackPressed() {
        val layoutManager = mViewBinding.rvGallery.layoutManager as LinearLayoutManager
        val currentPosition = layoutManager.findFirstVisibleItemPosition()
        val viewHolder = mViewBinding.rvGallery.findViewHolderForAdapterPosition(currentPosition)
        val imageView = viewHolder?.itemView?.findViewById<ProportionalImageView>(R.id.iv_gallery)
        imageView?.let {
            ViewCompat.setTransitionName(it, "${getString(com.lazyxu.lib_common.R.string.video_share_element_imageview)}_${currentPosition}")
        }
        setResult(RESULT_OK, Intent().apply {
            putExtra(Constants.KEY_GALLERY_BACK_POS, currentPosition)
        })
        supportFinishAfterTransition()
    }

}
