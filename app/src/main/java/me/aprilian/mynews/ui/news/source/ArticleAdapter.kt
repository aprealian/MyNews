package me.aprilian.mynews.ui.news.source

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aprilian.mynews.R
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.utils.load
import me.aprilian.mynews.core.view.BaseRVAdapter
import me.aprilian.mynews.databinding.ItemArticleBinding
import me.aprilian.mynews.domain.Article

class ArticleAdapter(ctx: Context?, resource: Resource<List<Article>>, private val clickListener: (Article?) -> Unit) : BaseRVAdapter<Article>(ctx, resource, loadingLayout = R.layout.item_article_loading) {

    class SourceViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SourceViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SourceViewHolder) {
            val item = resource.data?.get(position)
            holder.binding.article = item
            ctx?.let { holder.binding.ivArticle.load(it, item?.urlToImage) }
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}