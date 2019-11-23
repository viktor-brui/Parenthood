package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository
import org.unicef.parenthood.repository.model.TestEntity

class CreateViewModel: ViewModel() {
    private val repository = Repository()

    fun addTest(test: TestEntity, articleId: String) {
        viewModelScope.launch {
            repository.addTest(test, articleId)
        }
    }
}