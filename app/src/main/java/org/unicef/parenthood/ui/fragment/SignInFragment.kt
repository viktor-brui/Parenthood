package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.unicef.parenthood.BuildConfig
import org.unicef.parenthood.databinding.FragmentSignInBinding
import org.unicef.parenthood.ui.viewmodel.SignInViewModel
import org.unicef.parenthood.util.observeEvent
import org.unicef.parenthood.util.toast

class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignInBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (BuildConfig.DEBUG) {
            binding.emailInputEditTextLogin.setText("matty@gmail.com")
            binding.passwordInputEditTextLogin.setText("123456")
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.emailInputEditTextLogin.text.toString()
            val password = binding.passwordInputEditTextLogin.text.toString()
            viewModel.onLoginClicked(email, password)
        }

        viewModel.isSuccessfulLogin.observeEvent(viewLifecycleOwner) { isSuccessful ->
            if (isSuccessful) {
                val direction = SignInFragmentDirections
                    .actionSignInFragmentToArticlesContainerFragment()
                findNavController().navigate(direction)
            } else {
                toast("Login failed")
            }
        }
    }
}
