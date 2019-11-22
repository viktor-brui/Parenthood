package org.unicef.parenthood.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.unicef.parenthood.ui.viewmodel.WelcomeViewModel
import org.unicef.parenthood.R

class WelcomeFragment : Fragment() {
    //todo


    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }
}
