package me.aprilian.mynews.datasource.api.response

import com.google.gson.annotations.SerializedName
import me.aprilian.mynews.domain.Article


data class ArticlesResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    data class Article(
        @SerializedName("author")
        val author: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("source")
        val source: Source?,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String?
    ) {
        data class Source(
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String
        )
    }
}

fun ArticlesResponse.Article.toDomainArticle(): Article {
    return Article(
        title = title,
        description = description ?: "",
        content = content ?: "",
        author = author ?: "",
        url = url,
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
        source = Article.Source(
            id = source?.id ?: "",
            name = source?.name ?: "",
        )
    )
}