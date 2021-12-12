package me.aprilian.mynews.core.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aprilian.mynews.R
import me.aprilian.mynews.core.data.Resource
import java.lang.Exception

abstract class BaseRVAdapter<T>(val ctx: Context?, var resource: Resource<List<T>>, private val loadingLayout: Int = R.layout.core_state_loading, private val emptyLayout: Int = R.layout.core_state_empty, private val errorLayout: Int = R.layout.core_state_error, private val listener: IAdapter? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected abstract fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    open var errorMessage = "Failed to load data"

    open fun createLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return try {
            LoadingItem(LayoutInflater.from(parent.context).inflate(loadingLayout, parent, false))
        } catch (e: Exception){
            LoadingItem(LayoutInflater.from(parent.context).inflate(R.layout.core_state_loading, parent, false))
        }
    }

    open fun createErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadingItem(LayoutInflater.from(parent.context).inflate(R.layout.core_state_error, parent, false))
    }

    open fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptyItem(LayoutInflater.from(parent.context).inflate(R.layout.core_state_empty, parent, false))
    }

    fun submitData(data: Resource<List<T>>?) {
        data?.let {
            resource = it
            //if (data.data?.isNullOrEmpty() == false) notifyItemRangeInserted(0, data.data.size)
            notifyDataSetChanged()
        }
    }

    fun addData(data: Resource<List<T>>?) {
        data?.let {
            val list = resource.data?.toMutableList() ?: arrayListOf()
            it.data?.let { list.addAll(it) }
            resource = Resource(Resource.Status.SUCCESS, list, "")
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> createDataViewHolder(parent)
            TYPE_LOADING -> createLoadingViewHolder(parent)
            TYPE_ERROR -> createErrorViewHolder(parent)
            TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> error("Unknown viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        if (resource.data.isNullOrEmpty()) {
            return when (resource.status) {
                Resource.Status.LOADING,
                Resource.Status.ERROR,
                Resource.Status.SUCCESS,
                Resource.Status.EMPTY -> 1
            }
        }

        return resource.data?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (resource.data.isNullOrEmpty()) {
            return when (resource.status) {
                Resource.Status.LOADING -> TYPE_LOADING
                Resource.Status.ERROR -> TYPE_ERROR
                Resource.Status.SUCCESS -> TYPE_EMPTY
                Resource.Status.EMPTY -> TYPE_EMPTY
            }
        }

        return TYPE_DATA
    }

    companion object {
        const val TYPE_DATA = 0
        const val TYPE_ERROR = 1
        const val TYPE_LOADING = 2
        const val TYPE_EMPTY = 3
    }

    inner class LoadingItem(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ErrorItem(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class EmptyItem(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface IAdapter{
        fun onEmptyButtonClick()
        fun onRefreshButtonClick()
    }
}