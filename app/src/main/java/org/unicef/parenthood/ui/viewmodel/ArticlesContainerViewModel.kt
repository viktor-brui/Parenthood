package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository
import org.unicef.parenthood.repository.model.ArticleEntity
import org.unicef.parenthood.util.Event

class ArticlesContainerViewModel : ViewModel() {

    private val repository = Repository()

    private val editorsPick = MutableLiveData<List<ArticleEntity>>()
    private val allArticles = MutableLiveData<List<ArticleEntity>>()

    private val _onArticleClicked = MutableLiveData<Event<Pair<ArticleEntity, Boolean>>>()
    val onArticleClicked: LiveData<Event<Pair<ArticleEntity, Boolean>>>
        get() = _onArticleClicked

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

    fun getArticles(position: Int): LiveData<List<ArticleEntity>> {
        return when (position) {
            0 -> editorsPick
            1 -> allArticles
            else -> throw IllegalStateException()
        }
    }

    fun onArticleClicked(article: ArticleEntity, upload: Boolean) {
        _onArticleClicked.value = Event(article to upload)
    }
}
