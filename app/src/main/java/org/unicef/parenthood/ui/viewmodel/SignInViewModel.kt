package org.unicef.parenthood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "SignUpViewModel"
    // val isSuccessfullLogin: LiveData<Boolean>

    init {

    }

    fun onLoginClicked(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }
}