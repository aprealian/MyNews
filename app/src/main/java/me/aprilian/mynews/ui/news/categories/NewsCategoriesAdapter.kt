package me.aprilian.mynews.ui.news.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aprilian.mynews.R
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.utils.load
import me.aprilian.mynews.core.view.BaseRVAdapter
import me.aprilian.mynews.databinding.ItemCategoryBinding
import me.aprilian.mynews.domain.Category

class NewsCategoriesAdapter(ctx: Context?, resource: Resource<List<Category>>, private val clickListener: (Category?) -> Unit) : BaseRVAdapter<Category>(ctx, resource, loadingLayout = R.layout.item_category_loading) {

    class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryViewHolder) {
            val item = resource.data?.get(position)
            holder.binding.category = item
            ctx?.let { holder.binding.ivMovie.load(it, item?.drawableId) }
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}