package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.unicef.parenthood.databinding.FragmentSignUpBinding
import org.unicef.parenthood.ui.viewmodel.SignUpViewModel

class SignUpFragment : Fragment()  {
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val email = binding.emailInputEditTextRegister.text.toString()
            val password = binding.passwordInputEditTextRegister.text.toString()
            viewModel.onRegisterClicked(email, password)
        }
    }
}
