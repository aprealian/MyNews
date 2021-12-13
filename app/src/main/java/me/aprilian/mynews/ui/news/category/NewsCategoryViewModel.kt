package me.aprilian.mynews.ui.news.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.datasource.api.response.SourceByCategoryResponse
import me.aprilian.mynews.datasource.reppository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsCategoryViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    private val currentPage: Int = 1
    private var sourceTag: String? = null

    fun setSourceTag(sourceTag: String){
        this.sourceTag = sourceTag
    }

    private val _sources = MutableLiveData<Resource<SourceByCategoryResponse>>()
    val sources: LiveData<Resource<SourceByCategoryResponse>> = _sources

    fun loadAllSource(){
        _sources.postValue(Resource.loading())

        viewModelScope.launch {
            sourceTag?.let {
                _sources.value = newsRepository.getSources(it, currentPage)
                //if (_sources.value?.status == Resource.Status.SUCCESS) currentPage.inc()
            } ?: kotlin.run {
                _sources.postValue(Resource.error("Source not found"))
            }
        }
    }

    fun incPage(){
        currentPage.inc()
    }

}