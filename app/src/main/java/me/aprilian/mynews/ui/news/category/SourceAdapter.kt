package me.aprilian.mynews.ui.news.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aprilian.mynews.R
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseRVAdapter
import me.aprilian.mynews.databinding.ItemSourceBinding
import me.aprilian.mynews.domain.Source

class SourceAdapter(ctx: Context?, resource: Resource<List<Source>>, private val clickListener: (Source?) -> Unit) : BaseRVAdapter<Source>(ctx, resource, loadingLayout = R.layout.item_source_loading) {

    class SourceViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SourceViewHolder(ItemSourceBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SourceViewHolder) {
            val item = resource.data?.get(position)
            holder.binding.source = item
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}