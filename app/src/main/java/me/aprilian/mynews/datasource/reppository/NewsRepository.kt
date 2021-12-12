package me.aprilian.mynews.datasource.reppository

import me.aprilian.mynews.BuildConfig
import me.aprilian.mynews.core.data.BaseDataSource
import me.aprilian.mynews.datasource.api.NewsApiServices
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiServices: NewsApiServices
): BaseDataSource() {

    suspend fun getSources(category: String, page: Int, pageSize: Int = 10) = getResult {
        newsApiServices.getSources(category,  page, pageSize, BuildConfig.API_KEY)
    }

    suspend fun getArticles(sourceId: String, page: Int, pageSize: Int = 10) = getResult {
        newsApiServices.getArticles(sourceId, page, pageSize, BuildConfig.API_KEY)
    }

}