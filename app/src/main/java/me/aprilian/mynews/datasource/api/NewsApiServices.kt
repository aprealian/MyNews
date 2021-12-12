package me.aprilian.mynews.datasource.api

import me.aprilian.mynews.datasource.api.response.ArticlesResponse
import me.aprilian.mynews.datasource.api.response.SourceByCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String,
    ): Response<SourceByCategoryResponse>

    @GET("everything")
    suspend fun getArticles(
        @Query("sources") sourceId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Response<ArticlesResponse>

}