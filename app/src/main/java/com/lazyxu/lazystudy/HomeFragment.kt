package com.lazyxu.lazystudy

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazyxu.lib_common.constant.Constants
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.utils.decoration.NormalItemDecoration
import com.lazyxu.base.utils.snaphelper.StartSnapHelper
import com.lazyxu.lazystudy.databinding.FragmentHomeBinding


class HomeFragment : BaseVbVmFragment<FragmentHomeBinding, HomeViewModel>() {
    private val rankAdapter by lazy {
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
    }

    override fun initData() {
        mViewModel.getVideoList(requireContext())
    }

    override fun createObserver() {
        mViewModel.videoList.observe(this) {
            rankAdapter.setList(it)
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
    }
}