//package com.lazyxu.login.network
//
//import kotlinx.coroutines.flow.flow
//
//class RepositoryImpl : Repository {
//
//
//    override suspend fun login(
//        name: String,
//        pwd: String,
//        onComplete: () -> Unit,
//        onError: (String?) -> Unit,
//    ) = flow {
//        // 网络请求
//        val loginResponse = loginApiService.login(
////            RequestJsonBody()
////            .add("username", name)
////            .add("password", pwd)
////            .createRequestBody()
//        )
//        loginResponse.suspendOnSuccess {
//            emit(this.data?.data)
//        }.onException {
//            onError(exception.message)
//        }
//    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
//
//}