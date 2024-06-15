package com.lazyxu.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lazyxu.login.entity.UserEntity
import com.lazyxu.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val _loginLiveData = MutableLiveData<UserEntity>()

    val loginLiveData: LiveData<UserEntity> = _loginLiveData
    fun login(name: String, pwd: String) {
        viewModelScope.launch {
            runCatching {
//                RepositoryImpl().login(name, pwd, onComplete = {}, onError = {})
            }.onSuccess {

            }.onFailure {

            }
        }
    }


}