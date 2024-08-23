package com.lazyxu.lazystudy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lazyxu.lib_common.constant.Constants
import com.lazyxu.base.ext.toBean
import com.lazyxu.base.utils.AssetsUtils
import com.lazyxu.lib_database.entity.VideoEntity
import com.lazyxu.network.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel() {
    private val _videoList = MutableLiveData<MutableList<VideoEntity>?>()
    var videoList: LiveData<MutableList<VideoEntity>?> = _videoList

    fun getVideoList(context: Context) {
        val json = AssetsUtils.loadJsonFromAsset(context, Constants.FILE_VIDEO_LIST)
        _videoList.postValue(json?.toBean<MutableList<VideoEntity>>())
    }
}