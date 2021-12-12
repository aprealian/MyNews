package me.aprilian.mynews.domain
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("category")
    val category: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    companion object{
        fun getAll(): ArrayList<Source> {
            return arrayListOf(
                Source(
                    id = "123",
                    category = "Business",
                    country = "England",
                    description = "Lorem ipsum",
                    language = "en",
                    name = "BBC News",
                    url = "https://www.bbc.com/news",
                ),
            )
        }
    }
}