package org.unicef.parenthood.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unicef.parenthood.repository.Repository

class TakeTestViewModel : ViewModel() {

    private val repository = Repository()

    private val _test = MutableLiveData<List<String>>()
    val test: LiveData<List<String>>
        get() = _test

    fun getTest(testId: String) {
        viewModelScope.launch {
            val loadedTest = repository.getTest(testId)
            if (loadedTest != null) {
                val qAndA = loadedTest.questions.flatMap { pair ->
                    pair.split("|")
                }
                _test.value = qAndA
            }
        }
    }
}