//package com.lazyxu.login.repository
//
//import com.lazyxu.login.entity.UserEntity
//import com.lazyxu.login.http.LoginApiService
//import com.lazyxu.network.RequestJsonBody
//import com.lazyxu.network.ResponseResult
//import com.lazyxu.network.base.BaseResponse
//import com.lazyxu.network.doFailure
//import com.lazyxu.network.suspendOnSuccess
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//
//class LoginRepositoryImpl(
//    val api: LoginApiService,
//) : Repository {
//    override suspend fun login(
//        name: String,
//        pwd: String
//    ): Flow<ResponseResult<BaseResponse<UserEntity>>> {
//        return flow {
//            val logindata = api.login(RequestJsonBody().add("username", name).add("password", pwd).build())
//            logindata.suspendOnSuccess {
////                emit(da)
//
//            }.doFailure {  }
////            if (logindata.S){
////                emit(ResponseResult.Success(logindata))
////            }else{
////                emit(ResponseResult.Success(logindata))
////            }
//        }
//    }
//}