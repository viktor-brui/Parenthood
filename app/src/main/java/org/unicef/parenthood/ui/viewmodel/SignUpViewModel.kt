package org.unicef.parenthood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.unicef.parenthood.util.Event

class SignUpViewModel : ViewModel() {
    private val TAG = "SignUpViewModel"

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isSuccessfullSignUp = MutableLiveData<Event<Boolean>>()
    val isSuccessfulSignUp: LiveData<Event<Boolean>>
        get() = _isSuccessfullSignUp

    fun onRegisterClicked(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()

                Log.d(TAG, "createUserWithEmail: success")

                _isSuccessfullSignUp.value = Event(true)
            } catch (e: Exception) {
                Log.w(TAG, "createUserWithEmail: failure", e)
                _isSuccessfullSignUp.value = Event(false)
            }
        }
    }
}
