package com.lazyxu.lazystudy

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.utils.decoration.NormalItemDecoration
import com.lazyxu.base.utils.snaphelper.StartSnapHelper
import com.lazyxu.lazystudy.databinding.FragmentCategoryBinding


class CategoryFragment : BaseVbVmFragment<FragmentCategoryBinding, HomeViewModel>() {
    private val galleryAdapter by lazy {
        GalleryAdapter()
    }

    override fun createObserver() {
        mViewModel.videoList.observe(this) {
            galleryAdapter.setList(it)
        }
    }

    override fun initData() {
        mViewModel.getVideoList(requireContext())
    }

    override fun initView() {

        mViewBinding.tvHotEffect.setOnClickListener {
            ARouterHelper.goActivity(ARouterPath.Search.SEARCH)
        }
        mViewBinding.rvGallery.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NormalItemDecoration(LinearLayoutManager.HORIZONTAL).apply {
                setBounds(left = 5.dp2px, right = 5.dp2px)
                removeStartEndMargin(true)
            })
            adapter = galleryAdapter
        }
        LinearSnapHelper().attachToRecyclerView(mViewBinding.rvGallery)
        val spacing = resources.getDimensionPixelSize(com.lazyxu.base.R.dimen.dp_50) // 定义间距大小
        mViewBinding.rvGallery.addItemDecoration(CenterZoomItemDecoration(spacing))
    }
}