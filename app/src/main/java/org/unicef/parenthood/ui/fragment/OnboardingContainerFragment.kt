package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.unicef.parenthood.adapters.OnboardingContainerPageAdapter
import org.unicef.parenthood.databinding.FragmentOnboardingMainBinding

/**
 * A simple [Fragment] subclass.
 */
class OnboardingContainerFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.fragment_onboarding_main, container, false)
        binding = FragmentOnboardingMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpagerOnboarding.adapter = OnboardingContainerPageAdapter()
    }
}
