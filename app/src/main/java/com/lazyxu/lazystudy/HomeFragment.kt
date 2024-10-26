package com.lazyxu.lazystudy

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.AnimationUtils
import com.lazyxu.base.utils.decoration.NormalItemDecoration
import com.lazyxu.base.utils.snaphelper.StartSnapHelper
import com.lazyxu.lazystudy.databinding.FragmentHomeBinding
import com.lazyxu.lazystudy.ui.GalleryActivity
import com.lazyxu.lib_common.UiUtils
import com.lazyxu.lib_common.constant.Constants
import com.lazyxu.lib_database.entity.VideoEntity


class HomeFragment : BaseVbVmFragment<FragmentHomeBinding, HomeViewModel>() {
    private val rankAdapter by lazy {
        RankAdapter()
    }
    private val picAdapter by lazy {
        RankAdapter()
    }

    override fun initView() {
        mViewBinding.rvRank.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NormalItemDecoration(LinearLayoutManager.HORIZONTAL).apply {
                setBounds(left = 5.dp2px, right = 5.dp2px)
                removeStartEndMargin(true)
            })
            adapter = rankAdapter
        }
        StartSnapHelper().attachToRecyclerView(mViewBinding.rvRank)
        mViewBinding.rvPic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NormalItemDecoration(LinearLayoutManager.HORIZONTAL).apply {
                setBounds(left = 5.dp2px, right = 5.dp2px)
                removeStartEndMargin(true)
            })
            adapter = picAdapter
        }
        StartSnapHelper().attachToRecyclerView(mViewBinding.rvPic)
    }

    override fun initData() {
        mViewModel.getVideoList(requireContext())
    }

    override fun createObserver() {
        mViewModel.videoList.observe(this) {
            rankAdapter.setList(it)
            picAdapter.setList(it)
        }
    }


    override fun initClicks() {
        rankAdapter.setOnItemClickListener { adapter, view, position ->
            ARouterHelper.goActivityNeedNet(
                ARouterPath.Video.PLAY,
                mapOf(
                    Constants.KEY_VIDEO_PLAY_LIST to adapter.data,
                    Constants.KEY_VIDEO_PLAY_POS to position
                )
            )
        }
        picAdapter.setOnItemClickListener { adapter, view, position ->
//            val pair1 = androidx.core.util.Pair<View, String>(
//                view.findViewById<AppCompatImageView>(R.id.iv_bg),
//                "${context?.getString(com.lazyxu.lib_common.R.string.video_share_element_imageview)}_${position}"
//            )
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mActivity!!,
                view.findViewById<AppCompatImageView>(R.id.iv_bg),
                "${context?.getString(com.lazyxu.lib_common.R.string.video_share_element_imageview)}_${position}"
            )
//            ARouterHelper.goActivity(
//                ARouterPath.PIC,
//                mapOf(
//                    Constants.KEY_VIDEO_PLAY_LIST to adapter.data,
//                    Constants.KEY_VIDEO_PLAY_POS to position
//                ),
//                options = activityOptionsCompat,
//                activity = activity
//            )
            val intent = Intent(requireContext(), GalleryActivity::class.java)
            intent.putParcelableArrayListExtra(
                Constants.KEY_VIDEO_PLAY_LIST,
                adapter.data as ArrayList<VideoEntity>
            )
            intent.putExtra(
                Constants.KEY_VIDEO_PLAY_POS,
                position
            )
            resultLauncher.launch(intent, activityOptionsCompat)
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val position = result.data?.getIntExtra(Constants.KEY_GALLERY_BACK_POS, -1) ?: -1
                val layoutManager = (mViewBinding.rvPic.layoutManager as LinearLayoutManager)
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                LogUtils.d("position=$position")
                if ((position != -1) && position !in firstVisiblePosition..lastVisiblePosition) {
                    mViewBinding.rvPic.scrollToPosition(position)
                }
            }
        }

    fun onActivityReenter(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val position = data?.getIntExtra(Constants.KEY_GALLERY_BACK_POS, -1) ?: -1
            val layoutManager = (mViewBinding.rvPic.layoutManager as LinearLayoutManager)
            val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
            val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
            LogUtils.d("position=$position")
            if (position != -1) {
//                if (position !in firstVisiblePosition..lastVisiblePosition){
//                    mViewBinding.rvPic.scrollToPosition(position)
//                }
                mViewBinding.rvPic.scrollToPosition(position)
                mViewBinding.rvPic.postDelayed({
                    val viewHolder = mViewBinding.rvPic.findViewHolderForAdapterPosition(position)
                    val imageView =
                        viewHolder?.itemView?.findViewById<AppCompatImageView>(R.id.iv_bg)
                    imageView?.let {
                        ViewCompat.setTransitionName(
                            it,
                            "${getString(com.lazyxu.lib_common.R.string.video_share_element_imageview)}_${position}"
                        )
                    }
                }, 100L)
            }
        }
    }
}