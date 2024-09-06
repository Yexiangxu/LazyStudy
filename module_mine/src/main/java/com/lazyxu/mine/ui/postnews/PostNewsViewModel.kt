package com.lazyxu.mine.ui.postnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lazyxu.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

open class PostNewsViewModel : BaseViewModel() {
//    var publishSubjectLiveData: MutableLiveData<CommunityItem?> = MutableLiveData()
//
//    /**
//     * 发动态
//     */
//    fun publishSubject(body: RequestBody) {
//        viewModelScope.launch {
//            runCatching {
//                loadingLiveData.postValue(true)
//                apiService.publishSubject(body)
//            }.onSuccess {
//                if (it.error_code == 0) {
//                    publishSubjectLiveData.postValue(it.data)
//                } else {
//                    ToastUtils.toast(it.error_message)
//                }
//                loadingLiveData.postValue(false)
//            }.onFailure {
//                loadingLiveData.postValue(false)
//            }
//        }
//    }
//
//    var uploadImgLiveData: MutableLiveData<String?> = MutableLiveData()
//
//    /**
//     * 图片上传，后端要求一张张上传
//     */
//    fun uploadImg(name: RequestBody, file: MultipartBody.Part) {
//        viewModelScope.launch {
//            runCatching {
//                apiService.uploadImg(name, file)
//            }.onSuccess {
//                if (it.error_code == 0) {
//                    uploadImgLiveData.value = it.data//用postvalue会出现只取到最后一次的value
//                } else {
//                    ToastUtils.toast(it.error_message)
//                    uploadImgLiveData.value = null
//                }
//            }.onFailure {
//                uploadImgLiveData.value = null
//                loadingLiveData.postValue(false)
//            }
//        }
//    }
}