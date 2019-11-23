package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository
import org.unicef.parenthood.repository.model.ArticleEntity
import org.unicef.parenthood.util.Event

class ArticleViewModel : ViewModel() {

    private val repository = Repository()

    private val _onArticleUploaded = MutableLiveData<Event<Unit>>()
    val onArticleUploaded: LiveData<Event<Unit>>
        get() = _onArticleUploaded

    fun uploadArticle(article: ArticleEntity) {
        viewModelScope.launch {
            repository.addArticle(article)
            _onArticleUploaded.value = Event(Unit)
        }
    }
}
