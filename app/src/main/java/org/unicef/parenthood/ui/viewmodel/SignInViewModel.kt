package org.unicef.parenthood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import org.unicef.parenthood.util.Event

class SignInViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val TAG = "SignUpViewModel"

    private val _isSuccessfulLogin = MutableLiveData<Event<Boolean>>()
    val isSuccessfulLogin: LiveData<Event<Boolean>>
        get() = _isSuccessfulLogin


    fun onLoginClicked(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    _isSuccessfulLogin.value = Event(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    _isSuccessfulLogin.value = Event(false)
                }
            }
    }
}