package org.unicef.parenthood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "SignUpViewModel"
    private val _isSuccessfullSignUp = MutableLiveData<Boolean>()
    val isSuccessfullSignUp: LiveData<Boolean>
        get() = _isSuccessfullSignUp

    fun onRegisterClicked(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    _isSuccessfullSignUp.value = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    _isSuccessfullSignUp.value = false
                }
            }
    }
}
