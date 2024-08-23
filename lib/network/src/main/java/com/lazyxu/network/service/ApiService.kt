package com.lazyxu.network.service

import com.lazyxu.lib_common.entity.SearchResult
import com.lazyxu.network.entity.SearchRecommend
import com.lazyxu.network.entity.base.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    suspend fun getSearchRecommend(): BaseResponse<MutableList<SearchRecommend>>

    /**
     * 搜索结果
     * @param page   页码
     * @param keyWord  关键词，支持多个，空格分开
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun getSearchResult(
        @Path("page") page: Int,
        @Field("k") keyWord: String
    ): BaseResponse<SearchResult?>
}