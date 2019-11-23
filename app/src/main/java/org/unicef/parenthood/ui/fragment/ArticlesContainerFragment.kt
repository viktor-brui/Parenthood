package org.unicef.parenthood.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.unicef.parenthood.ui.viewmodel.ArticlesContainerViewModel
import org.unicef.parenthood.R

class ArticlesContainerFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesContainerFragment()
    }

    private lateinit var viewModel: ArticlesContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticlesContainerViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
