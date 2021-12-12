package me.aprilian.mynews.domain

import com.google.gson.annotations.SerializedName
import me.aprilian.mynews.R
import java.io.Serializable

data class Category(
    @SerializedName("title")
    val title: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("drawableId")
    val drawableId: Int,
) : Serializable {
    companion object{
        fun getAllCategories(): ArrayList<Category> {
            return arrayListOf(
                Category(
                    title = "General",
                    tag = "general",
                    drawableId = R.drawable.ic_category_whiteboard
                ),
                Category(
                    title = "Business",
                    tag = "business",
                    drawableId = R.drawable.ic_category_briefcase
                ),
                Category(
                    title = "Technology",
                    tag = "technology",
                    drawableId = R.drawable.ic_category_laptop
                ),
                Category(
                    title = "Entertainment",
                    tag = "Entertainment",
                    drawableId = R.drawable.ic_category_color_palette
                ),
                Category(
                    title = "Science",
                    tag = "Science",
                    drawableId = R.drawable.ic_category_atom
                ),
                Category(
                    title = "Health",
                    tag = "Health",
                    drawableId = R.drawable.ic_category_dna
                ),
                Category(
                    title = "Sports",
                    tag = "Sports",
                    drawableId = R.drawable.ic_category_field
                )
            )
        }
    }
}