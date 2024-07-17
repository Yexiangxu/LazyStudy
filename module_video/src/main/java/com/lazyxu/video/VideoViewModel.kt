package com.lazyxu.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lib_common.entity.SearchResult
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lib_database.entity.SearchEntity
import com.lazyxu.lib_database.manager.LazyListDaoManager
import com.lazyxu.network.ApiManager
import com.lazyxu.network.entity.SearchRecommend
import com.lazyxu.network.respose.ApiResponse
import com.lazyxu.network.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * User:Lazy_xu
 * Date:2024/05/30
 * Description:
 * FIXME
 */
class VideoViewModel : BaseViewModel() {


}