package com.lazyxu.login.network

import com.lazyxu.login.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun login(
        name: String,
        pwd: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<UserEntity?>
}