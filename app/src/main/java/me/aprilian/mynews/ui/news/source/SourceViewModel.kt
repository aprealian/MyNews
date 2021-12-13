package me.aprilian.mynews.ui.news.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.datasource.api.response.ArticlesResponse
import me.aprilian.mynews.datasource.reppository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    private val currentPage: Int = 1
    private var sourceTag: String? = null

    fun setSourceTag(sourceTag: String){
        this.sourceTag = sourceTag
    }

    private val _articles = MutableLiveData<Resource<ArticlesResponse>>()
    val articles: LiveData<Resource<ArticlesResponse>> = _articles

    fun loadAllNews(){
        if (_articles.value?.status != Resource.Status.LOADING)

        _articles.postValue(Resource.loading())
        viewModelScope.launch {
            sourceTag?.let {
                _articles.value = newsRepository.getArticles(it, currentPage)
                if (_articles.value?.status == Resource.Status.SUCCESS) currentPage.inc()
            } ?: kotlin.run {
                _articles.postValue(Resource.error("Source not found"))
            }
        }
    }

}