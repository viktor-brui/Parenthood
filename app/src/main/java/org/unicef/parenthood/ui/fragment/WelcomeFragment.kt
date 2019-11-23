package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.unicef.parenthood.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {

            val direction = WelcomeFragmentDirections
                .actionWelcomeFragmentToSignInFragment()
            findNavController().navigate(direction)


        }
        binding.btnSignUp.setOnClickListener {
            val direction = WelcomeFragmentDirections
                .actionWelcomeFragmentToSignUpFragment()
            findNavController().navigate(direction)
        }
    }
}
