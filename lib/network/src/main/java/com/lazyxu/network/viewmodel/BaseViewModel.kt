package com.lazyxu.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class BaseViewModel  : ViewModel() {

    fun launchFlow(){
        viewModelScope.launch(Dispatchers.Main) {
//            val data = requestFlow(errorBlock = { code, error ->
//                if (ERROR.UNLOGIN.code == code) {
//                    errorCall?.onLoginFail(code, error)
//                } else {
//                    errorCall?.onError(code, error)
//                }
//            }, requestCall, showLoading)
//            successBlock(data)
        }
    }
}