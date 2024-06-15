package com.lazyxu.search

import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbVmActivity
import com.lazyxu.base.ext.gone
import com.lazyxu.base.ext.visible
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.network.entity.base.PageInfo
import com.lazyxu.search.databinding.ActivitySearchBinding

/**
 * User:Lazy_xu
 * Date:2024/05/29
 * Description:
 * FIXME
 */
@Route(path = ARouterPath.Search.SEARCH)
class SearchActivity : BaseVbVmActivity<ActivitySearchBinding, SearchViewModel>() {
    private var pageInfo = PageInfo()
    private var searchTag = ""
//    private val searchResultAdapter: SearchResultAdapter by lazy {
//        SearchResultAdapter()
//    }

    override fun createObserver() {
        mViewModel.searchHistoryList.observe(this) {
            LogUtils.d(LogTag.SEARCH, "searchhistory=${it}")
            if (it.isNullOrEmpty()) {
                mViewBinding.shvHistory.gone()
            } else {
                mViewBinding.shvHistory.visible()
                mViewBinding.shvHistory.setHistoryData(it.map { it.keyTag })
            }
        }
        mViewModel.searchRecommendList.observe(this) {
            if (it.isNullOrEmpty()) {
                mViewBinding.shvRecommend.gone()
            } else {
                mViewBinding.shvRecommend.visible()
                mViewBinding.shvRecommend.setHistoryData(it.map { it.name })
            }
        }
        mViewModel.searchResult.observe(this) {
//            it.size
//            if (pageInfo.isFirstPage) {
//                searchResultAdapter.setData(it)
//                if (it.is()) {
//                    //空视图
//                    mViewBinding.viewEmptyData.visible()
//                } else {
//                    mBinding.viewEmptyData.gone()
//                }
//            } else {
//                mAdapter.addAll(it)
//                mBinding.refreshLayout.finishLoadMore()
//            }
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mViewBinding.rvSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
//            adapter = searchResultAdapter
        }
//        searchResultAdapter.setOnItemClickListener { adapter, view, position ->
//        }
        mViewBinding.srlSearch.apply {
            setEnableRefresh(false)
            setEnableLoadMore(true)
            autoRefresh()
            setOnLoadMoreListener {
                pageInfo.nextPage()

            }
        }

    }

    override fun initClicks() {
        mViewBinding.ivBack.setOnClickListener { onBackPressed() }
        mViewBinding.etSearch.addTextChangedListener {
            val content = it.toString()
            if (content.isEmpty()) {
                mViewBinding.clSearchResult.gone()
            }
        }
        mViewBinding.tvSearch.setOnClickListener { getSearchResult() }
        mViewBinding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getSearchResult()
            }
            return@setOnEditorActionListener false
        }
        mViewBinding.shvHistory.setOnCheckChangeListener {
            mViewBinding.etSearch.setText(it)
            getSearchResult()
        }
        mViewBinding.shvHistory.setOnLongClickListener {
            LogUtils.d(LogTag.SEARCH, "长按=${it.id}")
            true
        }
        mViewBinding.shvHistory.setOnDrawEndClickListener {
            mViewModel.delete()
        }
    }

    override fun initData() {
        mViewModel.getSearchHistory()
        mViewModel.getRecommendSearch()
    }


    private fun getSearchResult() {
        val searchtag = mViewBinding.etSearch.text.toString().trim()
        if (searchtag.isEmpty()) {
            return
        }
        this.searchTag = searchtag
        pageInfo.reset()
        mViewModel.getSearchResult(pageInfo.page, searchtag)
    }
}