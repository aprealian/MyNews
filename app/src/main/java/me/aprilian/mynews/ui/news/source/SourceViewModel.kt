package me.aprilian.mynews.ui.news.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.domain.Article
import me.aprilian.mynews.domain.Source

class SourceViewModel : ViewModel(){

    private val currentPage: Int = 1
    private var sourceTag: String? = null

    fun setSourceTag(sourceTag: String){
        this.sourceTag = sourceTag
    }

    private val _articles = MutableLiveData<Resource<List<Article>>>()
    val articles: LiveData<Resource<List<Article>>> = _articles

    private fun loadAllNews(){
        _articles.postValue(Resource.loading())
        _articles.postValue(Resource.success(Article.getAll()))
    }

    init {
        loadAllNews()
    }
}