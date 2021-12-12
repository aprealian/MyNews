package me.aprilian.mynews.ui.news.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.domain.Category

class NewsCategoriesViewModel : ViewModel(){

    private val _categories = MutableLiveData<Resource<List<Category>>>()
    val categories: LiveData<Resource<List<Category>>> = _categories

    private fun loadCategories(){
        _categories.postValue(Resource.loading())
        _categories.postValue(Resource.success(Category.getAllCategories()))
    }

    init {
        loadCategories()
    }
}