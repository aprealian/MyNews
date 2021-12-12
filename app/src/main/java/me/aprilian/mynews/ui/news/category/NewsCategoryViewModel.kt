package me.aprilian.mynews.ui.news.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.domain.Source

class NewsCategoryViewModel : ViewModel(){

    private val currentPage: Int = 1
    private var sourceTag: String? = null

    fun setSourceTag(sourceTag: String){
        this.sourceTag = sourceTag
    }

    private val _sources = MutableLiveData<Resource<List<Source>>>()
    val sources: LiveData<Resource<List<Source>>> = _sources

    private fun loadAllNews(){
        _sources.postValue(Resource.loading())
        _sources.postValue(Resource.success(Source.getAll()))
    }

    init {
        loadAllNews()
    }
}