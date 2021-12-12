package me.aprilian.mynews.domain
import com.google.gson.annotations.SerializedName


data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
) {
    data class Source(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String
    )

    companion object{
        fun getSample(): Article {
            return Article(
                author = "Corey Kilgannon",
                content = "The plant opening northeast of Niagara Falls this month, in Somerset, N.Y., is part of a \$550 million project by Terawulf, a Bitcoin mining company. The project also includes a proposed 150-megawatt … [+1514 chars]",
                description = "Cryptocurrency miners are flocking to New York’s faded industrial towns, prompting concern over the environmental impact of huge computer farms.",
                publishedAt = "2021-12-06T00:42:28Z",
                source = Source(null, "New York Times"),
                title = "Why New York State Is Experiencing a Bitcoin Boom",
                url = "https://www.nytimes.com/2021/12/05/nyregion/bitcoin-mining-upstate-new-york.html",
                urlToImage = "https://static01.nyt.com/images/2021/11/25/nyregion/00nybitcoin5/00nybitcoin5-facebookJumbo.jpg",
            )
        }
        fun getAll(): ArrayList<Article> {
            return arrayListOf(
                getSample(),
                getSample(),
                getSample(),
            )
        }
    }
}