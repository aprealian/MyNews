package me.aprilian.mynews.domain

data class Category(
    val title: String,
    val tag: String
) {
    companion object{
        fun getAllCategories(): ArrayList<Category> {
            return arrayListOf(
                Category(
                    title = "General",
                    tag = "general"
                ),
                Category(
                    title = "Business",
                    tag = "business"
                ),
                Category(
                    title = "Technology",
                    tag = "technology"
                ),
                Category(
                    title = "Entertainment",
                    tag = "Entertainment"
                ),
                Category(
                    title = "Science",
                    tag = "Science"
                ),
                Category(
                    title = "Health",
                    tag = "Health"
                ),
                Category(
                    title = "Sports",
                    tag = "Sports"
                )
            )
        }
    }
}