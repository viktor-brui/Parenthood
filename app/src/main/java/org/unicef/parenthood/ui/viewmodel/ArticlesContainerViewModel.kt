package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository
import org.unicef.parenthood.repository.model.ArticleEntity

class ArticlesContainerViewModel : ViewModel() {
    private val editorsPick = MutableLiveData<List<ArticleEntity>>()
    private val allArticles = MutableLiveData<List<ArticleEntity>>()
    private val repository = Repository()

    init {
        loadArticles()
    }

    fun loadArticles() {
        getEditorsPick()
        getAllArticles()
    }

    private fun getEditorsPick() {
        viewModelScope.launch {
            val recommended = repository.getRecommended()
            editorsPick.value = recommended
        }
    }

    private fun getAllArticles() {
        viewModelScope.launch {
            val all = repository.getDiscoverable()
            allArticles.value = all
        }
    }

    fun getArticlesById(position: Int): LiveData<List<ArticleEntity>> {
        return when(position) {
            0 -> editorsPick
            1 -> allArticles
            else -> throw IllegalStateException()
        }
    }
}
