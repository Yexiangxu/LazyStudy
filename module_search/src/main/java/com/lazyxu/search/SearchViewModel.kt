package com.lazyxu.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lib_common.entity.SearchResult
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lib_database.entity.SearchEntity
import com.lazyxu.lib_database.manager.LazyListDaoManager
import com.lazyxu.network.entity.SearchRecommend
import com.lazyxu.network.manager.ApiManager
import com.lazyxu.network.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * User:Lazy_xu
 * Date:2024/05/30
 * Description:
 * FIXME
 */
class SearchViewModel : BaseViewModel() {

    fun insertSearch(searchEntity: SearchEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            LazyListDaoManager.insertSearch(searchEntity)
            _searchHistoryList.postValue(LazyListDaoManager.getAllSearch())
        }
    }

    private val _searchHistoryList = MutableLiveData<MutableList<SearchEntity>?>()
    var searchHistoryList: LiveData<MutableList<SearchEntity>?> = _searchHistoryList
    fun getSearchHistory() {
        LogUtils.d(LogTag.SEARCH, "获取搜索记录")
        viewModelScope.launch(Dispatchers.IO) {
            _searchHistoryList.postValue(LazyListDaoManager.getAllSearch())
        }
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            LazyListDaoManager.delete()
            _searchHistoryList.postValue(LazyListDaoManager.getAllSearch())
        }
    }

    private val _searchRecommendList = MutableLiveData<MutableList<SearchRecommend>?>()
    var searchRecommendList: LiveData<MutableList<SearchRecommend>?> = _searchRecommendList
    fun getRecommendSearch() {
        viewModelScope.launch {
            runCatching {
                ApiManager.apiService.getSearchRecommend()
            }.onSuccess {
                _searchRecommendList.postValue(it.data)
            }
        }
    }

    private val _searchResult = MutableLiveData<SearchResult?>()
    var searchResult: LiveData<SearchResult?> = _searchResult
    fun getSearchResult(page: Int, keyWord: String) {
        viewModelScope.launch {
            runCatching {
                ApiManager.apiService.getSearchResult(page, keyWord)
            }.onSuccess {
                _searchResult.postValue(it.data)
                insertSearch(SearchEntity(keyTag = keyWord))
            }
        }
    }
}