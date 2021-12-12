package me.aprilian.mynews.datasource.api.response
import com.google.gson.annotations.SerializedName
import me.aprilian.mynews.domain.Source

data class SourceByCategoryResponse(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String?
) {
    data class Source(
        @SerializedName("category")
        val category: String?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("id")
        val id: String,
        @SerializedName("language")
        val language: String?,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String?
    )
}

fun SourceByCategoryResponse.Source.toDomainSource(): Source {
    return Source(
        id = id,
        name = name,
        description = description ?: "",
        category = category ?: "",
        url = url ?: "",
        country = country ?: "",
        language = language ?: "",
    )
}