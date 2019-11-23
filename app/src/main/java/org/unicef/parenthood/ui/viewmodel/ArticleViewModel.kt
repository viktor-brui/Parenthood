package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository
import org.unicef.parenthood.repository.model.ArticleEntity

class ArticleViewModel : ViewModel() {

    private val repository = Repository()

    fun uploadArticle(article: ArticleEntity) {
        viewModelScope.launch {
            repository.addArticle(article)
        }
    }
}
