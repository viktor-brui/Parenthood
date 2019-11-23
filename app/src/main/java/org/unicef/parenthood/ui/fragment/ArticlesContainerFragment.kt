package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_articles_container.*
import org.unicef.parenthood.adapters.ArticlesContainerPageAdapter
import org.unicef.parenthood.databinding.FragmentArticlesContainerBinding
import org.unicef.parenthood.ui.viewmodel.ArticlesContainerViewModel

class ArticlesContainerFragment : Fragment() {

    private lateinit var binding: FragmentArticlesContainerBinding
    private val viewModel: ArticlesContainerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.fragment_articles_container, container, false)
        binding = FragmentArticlesContainerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter = ArticlesContainerPageAdapter()
        TabLayoutMediator(binding.tabview, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Editor's Pick"
                    1 -> "All"
                    else -> "All"
                }
            }).attach()
    }
}
